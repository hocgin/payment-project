package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.PaymentException;
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
import lombok.experimental.Accessors;

import java.util.Map;

import static in.hocg.payment.wxpay.constants.Constants.RESPONSE_SUCCESS_CODE;

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
    
    /**
     * APPID
     */
    @ApiField(value = "app_id", required = true)
    @XStreamAlias("app_id")
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
    
    public String toXML() {
        XStream xstream = Helpers.newXStream();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }
    
    public R request(String uri, Class<R> responseClass) {
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
    
        String url = String.format("%s/%s", configStorage.getUrl(), uri);
        String response = Helpers.getHttpClient().post(url, this.toXML());
        R result = InitializingBean.from(WxPayConverts.XML, response, responseClass);
        
        // 业务结果检查
        if (!RESPONSE_SUCCESS_CODE.equals(result.getReturnCode())) {
            throw PaymentException.wrap("业务处理失败: " + result.getReturnCode());
        }
        
        // 验签
        if (!result.checkSign(signType, key)) {
            throw PaymentException.wrap("签名校验失败，数据可能被串改");
        }
        return result;
    }
}
