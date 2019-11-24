package in.hocg.payment.net;

import java.net.Proxy;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class HttpClient {
    
    private static HttpClient SELF;
    
    public static <T extends HttpClient> HttpClient getInstance(Class<T> httpClientClass) {
        if (SELF == null) {
            try {
                SELF = httpClientClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return SELF;
    }
    
    /**
     * 设置代理
     *
     * @param proxy
     * @return
     */
    public abstract HttpClient proxy(Proxy proxy);
    
    
    /**
     * GET 请求
     * @param url
     * @param headers
     * @param responseClass
     * @param <T>
     * @return
     */
    public abstract <T> T get(String url, Map<String, String> headers, Class<T> responseClass);
}
