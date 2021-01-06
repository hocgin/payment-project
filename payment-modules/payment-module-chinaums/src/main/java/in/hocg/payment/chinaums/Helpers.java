package in.hocg.payment.chinaums;

import in.hocg.payment.sign.SignScheme;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.HttpUtils;
import lombok.experimental.UtilityClass;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class Helpers {

    /**
     * 支付宝签名策略
     */
    public static SignValue newSignValue() {
        SignValue signValue = new SignValue();
        return signValue.setFilter(entry -> Objects.nonNull(entry.getValue()))
            .setOrderStrategy(SignValue.KeyOrder.ASC);
    }

    /**
     * 获取URL
     *
     * @param baseUrl
     * @param values
     * @return
     */
    public static String getUrl(String baseUrl, Map<String, Object> values) {
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            Object value = entry.getValue();
            entry.setValue(URLEncoder.encode(String.valueOf(value)));
        }
        return HttpUtils.getUrl(baseUrl, values);
    }

    public static SignScheme getSignType(SignType signType) {
        if (SignType.SHA256.equals(signType)) {
            return SignType.SHA256.useLogger();
        }
        throw new UnsupportedOperationException();
    }

    public static SignScheme getSignType(String signType) {
        if ("SHA256".equalsIgnoreCase(signType)) {
            return SignType.SHA256;
        }
        throw new UnsupportedOperationException();
    }
}
