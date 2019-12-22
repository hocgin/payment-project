package in.hocg.payment.net;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class HttpClientFactory {
    
    private static HttpClient SELF;
    
    /**
     * 获取一个共享的 HttpClient
     *
     * @param httpClientClass
     * @param <T>
     * @return
     */
    public static <T extends HttpClient> HttpClient getSingleInstance(Class<T> httpClientClass) {
        if (SELF == null) {
            SELF = newInstance(httpClientClass);
        }
        return SELF;
    }
    
    /**
     * 创建一个 HttpClient
     *
     * @param httpClientClass
     * @param <T>
     * @return
     */
    public static <T extends HttpClient> HttpClient newInstance(Class<T> httpClientClass) {
        try {
            return new HttpClientLogger(httpClientClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
