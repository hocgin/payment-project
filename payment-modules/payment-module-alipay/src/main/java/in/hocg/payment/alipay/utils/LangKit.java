package in.hocg.payment.alipay.utils;

import in.hocg.payment.net.HttpClient;
import in.hocg.payment.net.ObjectHttpClient;
import in.hocg.payment.net.HttpClientFactory;
import in.hocg.payment.net.OkHttpClient;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class LangKit {
    
    /**
     * 获取一个 HTTP 客户端
     *
     * @return
     */
    public static ObjectHttpClient getHttpClient() {
        HttpClient httpClient = HttpClientFactory.getSingleInstance(OkHttpClient.class);
        return new ObjectHttpClient(httpClient);
    }
}
