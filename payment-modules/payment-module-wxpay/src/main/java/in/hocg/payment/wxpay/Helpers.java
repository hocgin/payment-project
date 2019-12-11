package in.hocg.payment.wxpay;

import in.hocg.payment.net.HttpClient;
import in.hocg.payment.net.HttpClientFactory;
import in.hocg.payment.net.OkHttpClient;
import in.hocg.payment.sign.SignValue;

import java.util.Objects;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Helpers {
    /**
     * 微信签名策略
     */
    public static SignValue newSignValue() {
        SignValue signValue = new SignValue();
        return signValue.setFilter(entry -> Objects.nonNull(entry.getValue()))
                .setOrderStrategy(SignValue.KeyOrder.ASC);
    }
    
    public static HttpClient getHttpClient() {
        return HttpClientFactory.getSingleInstance(OkHttpClient.class);
    }
}
