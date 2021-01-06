package in.hocg.payment.chinaums.v4_8.request;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.bean.TextInitializingBean;
import in.hocg.payment.chinaums.Helpers;
import in.hocg.payment.chinaums.convert.ChinaUmsConverts;
import in.hocg.payment.chinaums.v4_8.ChinaUmsConfigStorage;
import in.hocg.payment.chinaums.v4_8.ChinaUmsPayService;
import in.hocg.payment.chinaums.v4_8.response.ChinaUmsPayResponse;
import in.hocg.payment.exception.PaymentException;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignScheme;
import in.hocg.payment.sign.SignValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Map;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class ChinaUmsPayRequest<R extends ChinaUmsPayResponse>
    extends PaymentRequest<ChinaUmsPayService, R> {
    public static final String FIELD_SIGN = "sign";
    public static final String EXPIRE_TIME = "expireTime";
    public static final String MSG_SRC = "msgSrc";
    public static final String TID = "tid";
    public static final String MID = "mid";
    public static final String REQUEST_TIMESTAMP = "requestTimestamp";
    public static final String SIGN_TYPE = "signType";


    protected R request(Class<R> responseClass) {
        ChinaUmsConfigStorage configStorage = getPaymentService().getConfigStorage();
        String baseUrl = configStorage.getUrl();

        Map<String, Object> values = handleRequestParams();

        String httpUrl = URI.create(baseUrl).resolve(getSuffixUrl()).toString();
        String response = getHttpClient().post(httpUrl, JSON.toJSONString(values));
        return handleResponse(responseClass, response);
    }

    protected String requestAsString() {
        ChinaUmsConfigStorage configStorage = getPaymentService().getConfigStorage();
        String baseUrl = configStorage.getUrl();

        Map<String, Object> values = handleRequestParams();

        String httpUrl = URI.create(baseUrl).resolve(getSuffixUrl()).toString();
        String url = Helpers.getUrl(httpUrl, values);
        return getHttpClient().get(url);
    }

    protected String getUrl() {
        ChinaUmsConfigStorage configStorage = getPaymentService().getConfigStorage();
        String baseUrl = configStorage.getUrl();
        Map<String, Object> values = handleRequestParams();

        String httpUrl = URI.create(baseUrl).resolve(getSuffixUrl()).toString();
        return Helpers.getUrl(httpUrl, values);
    }

    protected String getSuffixUrl() {
        return "/netpay-route-server/api/";
    }

    protected R handleResponse(Class<R> responseClass, String response) {
        ChinaUmsConfigStorage configStorage = getPaymentService().getConfigStorage();
        SignScheme signType = configStorage.getSignType().useLogger();
        @NonNull String publicKey = configStorage.getTxmKey();
        R result = TextInitializingBean.from(ChinaUmsConverts.JSON_CONVERT, response, responseClass);

        // 如果签名失败
        if (!result.checkSign(signType, publicKey)) {
            throw new PaymentException("响应签名校验失败，数据可能被串改");
        }
        return result;
    }

    protected Map<String, Object> handleRequestParams() {
        return this.handleRequestParams(Maps.newHashMap());
    }

    protected Map<String, Object> handleRequestParams(Map<String, Object> values) {
        ChinaUmsConfigStorage configStorage = getPaymentService().getConfigStorage();
        SignScheme signType = Helpers.getSignType(configStorage.getSignType());

        // 设置参数
        String mid = configStorage.getMid();
        String tid = configStorage.getTid();
        String msgSrc = configStorage.getMsgSrc();

        @NonNull String privateKey = configStorage.getTxmKey();
        values.putAll(SignObjects.getSignValues(this));
        values.put(MSG_SRC, msgSrc);
        values.put(TID, tid);
        values.put(MID, mid);
        values.put(REQUEST_TIMESTAMP, DateUtil.now());
        values.put(SIGN_TYPE, signType.string().toUpperCase());

        SignValue signValue = Helpers.newSignValue().handle(values);
        String data = signValue.getSignValue();
        values = signValue.getHandledValues();

        String sign = signType.sign(data, privateKey);
        values.put(FIELD_SIGN, sign);
        return values;
    }


    protected String getMerOrderId(String orderSn) {
        ChinaUmsConfigStorage configStorage = getPaymentService().getConfigStorage();
        return configStorage.getMsgSrcId() + orderSn;
    }
}
