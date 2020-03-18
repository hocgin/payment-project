package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.JSON;
import in.hocg.payment.alipay.Helpers;
import in.hocg.payment.alipay.constants.Constants;
import in.hocg.payment.alipay.convert.AliPayConverts;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.response.AliPayResponse;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.bean.TextInitializingBean;
import in.hocg.payment.exception.PaymentException;
import in.hocg.payment.net.HttpClient;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignScheme;
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
        SignScheme signType = configStorage.getSignType().useLogger();

        // 设置参数
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.format = LangUtils.getOrDefault(this.getFormat(), configStorage.getFormat());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), LocalDateTime.now().format(Constants.ALIPAY_API_DATE_FORMAT));
        this.version = LangUtils.getOrDefault(this.getVersion(), configStorage.getVersion());
        this.notifyUrl = LangUtils.getOrDefault(this.getNotifyUrl(), null);
        this.signType = LangUtils.getOrDefault(this.getSignType(), signType.string());

        @NonNull String privateKey = configStorage.getPrivateKey();
        Map<String, Object> values = SignObjects.getSignValues(this);
        SignValue signValue = Helpers.newSignValue().handle(values);
        String data = signValue.getSignValue();
        values = signValue.getHandledValues();

        this.sign = signType.sign(data, privateKey);
        values.put(AliPayRequest.FIELD_SIGN, this.sign);
        return values;
    }

    @Override
    protected HttpClient getHttpClient() {
        return Helpers.getHttpClient();
    }

    /**
     * 发起请求
     *
     * @param responseClass
     * @return
     */
    protected R request(Class<R> responseClass) {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        String baseUrl = configStorage.getUrl();

        Map<String, Object> values = handleRequestParams();

        // 访问支付宝接口
        String url = Helpers.getUrl(baseUrl, values);
        String response = getHttpClient().get(url);
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
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        SignScheme signType = configStorage.getSignType().useLogger();
        @NonNull String aliPayPublicKey = configStorage.getAliPayPublicKey();
        R result = TextInitializingBean.from(AliPayConverts.JSON, response, responseClass);

        // 如果签名失败
        if (!result.checkSign(signType, aliPayPublicKey)) {
            throw new PaymentException("响应签名校验失败，数据可能被串改");
        }
        return result;
    }

    /**
     * 构建为 <form/>
     *
     * @return
     */
    protected R buildForm(Class<R> responseClass) {
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
            form.append(String.format("<input type=\"hidden\" name='%s' value='%s'/>", key, value));
        }
        form.append("<input type=\"submit\" value=\"立即支付\" style=\"display:none\"/>");
        form.append("</form>");
        form.append("<script>document.forms[0].submit();</script>");

        final String response = form.toString();
        return TextInitializingBean.from(AliPayConverts.TEXT, response, responseClass);
    }

    /**
     * 构建 URL
     *
     * @return
     */
    protected R buildSdkParams(Class<R> responseClass) {
        Map<String, Object> values = handleRequestParams();
        values.entrySet().forEach(entry -> {
            final Object value = entry.getValue();
            entry.setValue(URLEncoder.encode(String.valueOf(value)));
        });

        final String response = StringUtils.mapToString(values, "&");
        return TextInitializingBean.from(AliPayConverts.TEXT, response, responseClass);
    }
}
