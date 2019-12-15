package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.PaymentException;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.core.InitializingBean;
import in.hocg.payment.core.PaymentRequest;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.LangUtils;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.convert.WxPayConverts;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.v1.WxPayConfigStorage;
import in.hocg.payment.wxpay.v1.WxPayService;
import in.hocg.payment.wxpay.v1.response.WxPayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class WxPayRequest<R extends WxPayResponse>
        extends PaymentRequest<WxPayService, R> {
    
    @ApiField(value = "appid", required = true)
    @XStreamAlias("appid")
    protected String appId;
    
    @ApiField(value = "mch_id", required = true)
    @XStreamAlias("mch_id")
    protected String mchId;
    
    @ApiField(value = "nonce_str", required = true)
    @XStreamAlias("nonce_str")
    protected String nonceStr;
    
    @ApiField(value = "sign", required = true, ignore = true)
    @XStreamAlias("sign")
    protected String sign;
    
    @ApiField(value = "sign_type")
    @XStreamAlias("sign_type")
    protected String signType;
    
    protected String toXML() {
        XStream xstream = Helpers.newXStream();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }
    
    /**
     * 处理请求参数
     */
    protected void handleRequestParams() {
        WxPayConfigStorage configStorage = this.getPaymentService().getConfigStorage();
        String key = configStorage.getKey();
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.mchId = LangUtils.getOrDefault(this.getMchId(), configStorage.getMchId());
        this.nonceStr = LangUtils.getOrDefault(this.getNonceStr(), String.valueOf(System.currentTimeMillis()));
        WxSignType signType = configStorage.getSignType();
        this.signType = LangUtils.getOrDefault(this.getSignType(), signType.string());
        
        Map<String, Object> values = SignObjects.getSignValues(this);
        SignValue signValue = Helpers.newSignValue().handle(values);
        String signString = signValue.getSignValue();
        signString += String.format("&key=%s", key);
        
        this.sign = signType.sign(signString, key);
    }
    
    /**
     * 发起请求
     *
     * @param uri
     * @param responseClass
     * @return
     */
    protected R request(String uri,
                        Convert convert,
                        Class<R> responseClass) {
        WxPayConfigStorage configStorage = this.getPaymentService().getConfigStorage();
        final String baseUrl = configStorage.getUrl();
        
        handleRequestParams();
        
        String url = String.format("%s/%s", baseUrl, uri);
        String response = Helpers.getHttpClient().post(url, this.toXML());
        
        return handleResponse(convert, responseClass, response);
    }
    
    /**
     * 处理响应结果
     *
     * @param responseClass
     * @param response
     * @return
     */
    protected R handleResponse(Convert convert,
                               Class<R> responseClass,
                               String response) {
        WxPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        @NonNull final String key = configStorage.getKey();
        @NonNull final WxSignType signType = configStorage.getSignType();
        R result = InitializingBean.from(convert, response, responseClass);
        
        // 验签
        if (!result.checkSign(signType, key)) {
            throw PaymentException.wrap("签名校验失败，数据可能被串改");
        }
        return result;
    }
    
    protected R requestXML(String uri,
                           Class<R> responseClass) {
        return request(uri, WxPayConverts.XML, responseClass);
    }
    
    
    protected R requestData(String uri,
                            Class<R> responseClass) {
        return request(uri, WxPayConverts.TEXT, responseClass);
    }
}
