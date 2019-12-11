package in.hocg.payment.alipay;

import in.hocg.payment.net.HttpClient;
import in.hocg.payment.net.HttpClientFactory;
import in.hocg.payment.net.ObjectHttpClient;
import in.hocg.payment.net.OkHttpClient;
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
    
    /**
     * 获取一个 HTTP 客户端
     *
     * @return
     */
    public static ObjectHttpClient getObjectHttpClient() {
        HttpClient httpClient = HttpClientFactory.getSingleInstance(OkHttpClient.class);
        return new ObjectHttpClient(httpClient);
    }
    
    public static HttpClient getHttpClient() {
        return HttpClientFactory.getSingleInstance(OkHttpClient.class);
    }
    
}
