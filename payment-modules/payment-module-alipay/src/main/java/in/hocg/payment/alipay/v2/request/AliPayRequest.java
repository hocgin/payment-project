package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import in.hocg.payment.PaymentException;
import in.hocg.payment.alipay.Helpers;
import in.hocg.payment.alipay.constants.Constants;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.response.AliPayResponse;
import in.hocg.payment.core.PaymentRequest;
import in.hocg.payment.core.PaymentResponse;
import in.hocg.payment.net.Converts;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.LangUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AliPayRequest<R extends PaymentResponse>
        extends PaymentRequest<AliPayService, R> {
    public static final String FIELD_SIGN = "sign";
    
    @ApiField(value = "app_id", required = true)
    protected String appId;
    
    @ApiField(value = "method", required = true)
    protected String method;
    
    @ApiField(value = "format", required = true)
    protected String format;
    
    @ApiField(value = "charset", required = true)
    protected String charset;
    
    @ApiField(value = "sign_type", required = true)
    protected String signType;
    
    @ApiField(value = "sign", ignore = true)
    protected String sign;
    
    @ApiField(value = "timestamp", required = true)
    protected String timestamp;
    
    @ApiField(value = "version", required = true)
    protected String version;
    
    @ApiField("notify_url")
    protected String notifyUrl;
    
    @ApiField("app_auth_token")
    protected String appAuthToken;
    
    @ApiField(value = "biz_content", required = true)
    protected String bizContent = "{}";
    
    /**
     * 设置公用属性
     */
    protected void setPublicValues() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.format = LangUtils.getOrDefault(this.getFormat(), configStorage.getFormat());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), LocalDateTime.now().format(Constants.ALIPAY_API_DATE_FORMAT));
        this.version = LangUtils.getOrDefault(this.getVersion(), configStorage.getVersion());
        this.notifyUrl = LangUtils.getOrDefault(this.getNotifyUrl(), null);
        SignType signType = configStorage.getSignType();
        this.signType = LangUtils.getOrDefault(this.getSignType(), signType.name());
    }
    
    /**
     * 签名处理
     *
     * @return
     */
    @NotNull
    protected Map<String, Object> setSignAndGetParams(@NonNull String privateKey,
                                                      SignType signType) {
        Map<String, Object> values = SignObjects.getSignValues(this);
        SignValue signValue = Helpers.newSignValue().handle(values);
        String data = signValue.getSignValue();
        values = signValue.getHandledValues();
        
        this.sign = signType.sign(data, privateKey);
        values.put(AliPayRequest.FIELD_SIGN, this.sign);
        return values;
    }
    
    /**
     * 处理响应
     *
     * @param response
     * @param key
     * @param signType
     * @param publicKey
     * @param convert
     * @return
     */
    public R wrapResponse(JSONObject response,
                          String key,
                          SignType signType,
                          String publicKey,
                          Converts convert,
                          Class<R> clazz) {
        String sign = response.getString(AliPayResponse.FIELD_SIGN);
        String responseString = response.getString(key);
        
        // 检查签名
        boolean verifySign = signType.verify(responseString, publicKey, sign);
        
        // 如果签名失败
        if (!verifySign) {
            throw PaymentException.wrap("签名校验失败，数据可能被串改");
        }
        
        String code = response.getJSONObject(key).getString(AliPayResponse.FIELD_CODE);
        
        // 如果业务处理失败
        if (!Constants.RESPONSE_SUCCESS_CODE.equals(code)) {
            throw PaymentException.wrap("业务处理失败: " + code);
        }
        
        // 封装响应结构
        return convert.convert(responseString, clazz);
    }
    
    /**
     * 封装 BizContent
     *
     * @param bizContent
     */
    public void setBizContent2(BizContent bizContent) {
        this.bizContent = bizContent.string();
    }
    
    interface BizContent {
        
        /**
         * 签名
         *
         * @return
         */
        default String string() {
            return JSON.toJSONString(this);
        }
    }
    
    protected R request(String method,
                        String responseKey,
                        Class<R> responseClass) {
        // 设置参数
        setPublicValues();
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        @NonNull String privateKey = configStorage.getPrivateKey();
        @NonNull String aliPayPublicKey = configStorage.getAliPayPublicKey();
        SignType signType = configStorage.getSignType();
        String baseUrl = configStorage.getUrl();
        this.method = method;
        
        // 签名
        Map<String, Object> values = setSignAndGetParams(privateKey, signType);
        
        // 访问支付宝接口
        String url = Helpers.getUrl(baseUrl, values);
        Converts convert = Converts.valueOf(this.format.toUpperCase());
        JSONObject response = Helpers.getObjectHttpClient().get(url, convert, JSONObject.class);
        return wrapResponse(response, responseKey, signType, aliPayPublicKey, convert, responseClass);
    }
}
