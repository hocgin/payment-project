package in.hocg.payment.net;

import javax.annotation.Nullable;
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
     *
     * @param url
     * @param headers
     * @return
     */
    String get(String url, Map<String, String> headers);

    String get(String url);

    /**
     * POST 请求
     *
     * @param url
     * @param headers
     * @param body
     * @return
     */
    String post(String url, Map<String, String> headers, String body);

    String post(String url, String body);

}
