package in.hocg.payment.alipay.http;

import in.hocg.payment.alipay.utils.LangKit;
import in.hocg.payment.net.HttpClient;

import java.net.Proxy;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AliPayHttpClient implements HttpClient {
    private static final HttpClient CLIENT = LangKit.getHttpClient();
    
    @Override
    public HttpClient proxy(Proxy proxy) {
        return CLIENT.proxy(proxy);
    }
    
    @Override
    public <T> T get(String url, Map<String, String> headers, Class<T> responseClass) {
//        CLIENT.get(url, headers, )
        return null;
    }
    
    @Override
    public <T> T get(String url, Class<T> responseClass) {
        return null;
    }
}
