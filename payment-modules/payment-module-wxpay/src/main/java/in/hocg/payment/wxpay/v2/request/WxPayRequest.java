package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.bean.TextInitializingBean;
import in.hocg.payment.convert.StringConvert;
import in.hocg.payment.exception.PaymentException;
import in.hocg.payment.net.HttpClient;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignScheme;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.LangUtils;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.convert.WxPayConverts;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.response.WxPayResponse;
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
        SignScheme signType = configStorage.getSignType().useLogger();
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
                        StringConvert convert,
                        Class<R> responseClass) {
        WxPayConfigStorage configStorage = this.getPaymentService().getConfigStorage();
        final String baseUrl = configStorage.getUrl();

        handleRequestParams();

        String url = String.format("%s/%s", baseUrl, uri);

        String response = getHttpClient().post(url, this.toXML());

        return handleResponse(convert, responseClass, response);
    }

    @Override
    protected HttpClient getHttpClient() {
        return Helpers.getHttpClient();
    }

    protected HttpClient getCertHttpClient() {
        final WxPayConfigStorage configStorage = this.getPaymentService().getConfigStorage();
        return Helpers.getCertHttpClient(configStorage);
    }

    /**
     * 处理响应结果
     *
     * @param responseClass
     * @param response
     * @return
     */
    protected R handleResponse(StringConvert convert,
                               Class<R> responseClass,
                               String response) {
        WxPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        @NonNull final String key = configStorage.getKey();
        @NonNull final SignScheme signType = configStorage.getSignType().useLogger();
        R result = TextInitializingBean.from(convert, response, responseClass);

        // 验签
        if (result.isSign() && !result.checkSign(signType, key)) {
            throw new PaymentException("响应签名校验失败，数据可能被串改");
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
