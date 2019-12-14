package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.JSON;
import in.hocg.payment.PaymentException;
import in.hocg.payment.alipay.Helpers;
import in.hocg.payment.alipay.constants.Constants;
import in.hocg.payment.alipay.convert.AliPayConverts;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.response.AliPayResponse;
import in.hocg.payment.core.ErrorContext;
import in.hocg.payment.core.InitializingBean;
import in.hocg.payment.core.PaymentRequest;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.LangUtils;
import in.hocg.payment.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AliPayRequest<R extends AliPayResponse>
        extends PaymentRequest<AliPayService, R> {
    public static final String FIELD_SIGN = "sign";
    
    @ApiField(value = "app_id", required = true)
    protected String appId;
    
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
     * 封装 BizContent
     *
     * @param bizContent
     */
    public AliPayRequest setBizContent2(BizContent bizContent) {
        this.bizContent = bizContent.string();
        return this;
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
    
    /**
     * 处理请求参数
     *
     * @return
     */
    @NotNull
    protected Map<String, Object> handleRequestParams() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        SignType signType = configStorage.getSignType();
        
        // 设置参数
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.format = LangUtils.getOrDefault(this.getFormat(), configStorage.getFormat());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), LocalDateTime.now().format(Constants.ALIPAY_API_DATE_FORMAT));
        this.version = LangUtils.getOrDefault(this.getVersion(), configStorage.getVersion());
        this.notifyUrl = LangUtils.getOrDefault(this.getNotifyUrl(), null);
        this.signType = LangUtils.getOrDefault(this.getSignType(), signType.name());
        
        @NonNull String privateKey = configStorage.getPrivateKey();
        Map<String, Object> values = SignObjects.getSignValues(this);
        SignValue signValue = Helpers.newSignValue().handle(values);
        String data = signValue.getSignValue();
        values = signValue.getHandledValues();
        
        this.sign = signType.sign(data, privateKey);
        values.put(AliPayRequest.FIELD_SIGN, this.sign);
        return values;
    }
    
    /**
     * 发起请求
     *
     * @param responseClass
     * @return
     */
    protected R request(Class<R> responseClass) {
        ErrorContext.instance().activity("正在发起请求: " + this.getClass());
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        String baseUrl = configStorage.getUrl();
        
        Map<String, Object> values = handleRequestParams();
        
        // 访问支付宝接口
        String url = Helpers.getUrl(baseUrl, values);
        String response = Helpers.getHttpClient().get(url);
        return handleResponse(responseClass, response);
    }
    
    /**
     * 处理响应结果
     *
     * @param responseClass
     * @param response
     * @return
     */
    protected R handleResponse(Class<R> responseClass, String response) {
        ErrorContext.instance().activity("正在处理响应: " + this.getClass()).object(response);
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        SignType signType = configStorage.getSignType();
        @NonNull String aliPayPublicKey = configStorage.getAliPayPublicKey();
        R result = InitializingBean.from(AliPayConverts.JSON, response, responseClass);
        
        // 如果业务处理失败
        if (!Constants.RESPONSE_SUCCESS_CODE.equals(result.getCode())) {
            log.warn("错误信息: {}", response);
            throw PaymentException.wrap("业务处理失败: " + result.getCode());
        }
        
        // 如果签名失败
        if (!result.checkSign(signType, aliPayPublicKey)) {
            throw PaymentException.wrap("签名校验失败，数据可能被串改");
        }
        return result;
    }
    
    /**
     * 构建为 <form/>
     *
     * @return
     */
    protected String buildForm() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        String baseUrl = configStorage.getUrl();
        
        Map<String, Object> values = handleRequestParams();
        StringBuilder form = new StringBuilder();
        form.append(String.format("<form method=\"POST\" action=\"%s\">", baseUrl));
        String key;
        Object value;
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            form.append(String.format("<input type=\"hidden\" name=\"%s\" value=\'%s\'/>", key, value));
        }
        form.append("<input type=\"submit\" value=\"立即支付\" style=\"display:none\"/>");
        form.append("</form>");
        form.append("<script>document.forms[0].submit();</script>");
        return form.toString();
    }
    
    /**
     * 构建 URL
     * @return
     */
    protected String buildSdkParams() {
        Map<String, Object> values = handleRequestParams();
        values.entrySet().forEach(entry -> {
            final Object value = entry.getValue();
            entry.setValue(URLEncoder.encode(String.valueOf(value)));
        });
        return StringUtils.mapToString(values, "&");
    }
}
