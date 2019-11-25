package in.hocg.payment.alipay.utils;

import in.hocg.payment.net.HttpClient;
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
    public static HttpClient getHttpClient() {
        return HttpClientFactory.getSingleInstance(OkHttpClient.class);
    }
}
