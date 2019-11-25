package in.hocg.payment.net;

import java.net.Proxy;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface HttpClient {
    
    /**
     * 设置代理
     *
     * @param proxy
     * @return
     */
    HttpClient proxy(Proxy proxy);
    
    
    /**
     * GET 请求
     * @param url
     * @param headers
     * @param responseClass
     * @param <T>
     * @return
     */
    <T> T get(String url, Map<String, String> headers, Class<T> responseClass);
}
