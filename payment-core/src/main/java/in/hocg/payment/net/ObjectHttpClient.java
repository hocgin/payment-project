package in.hocg.payment.net;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.Proxy;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RequiredArgsConstructor
public class ObjectHttpClient {
    
    @NonNull
    private HttpClient httpClient;
    
    public ObjectHttpClient proxy(Proxy proxy) {
        httpClient.proxy(proxy);
        return this;
    }
    
    public <T> T get(String url,
                     Map<String, String> headers,
                     Convert convert,
                     Class<T> responseClass) {
        String responseBody = httpClient.get(url, headers);
        return convert.convert(responseBody, responseClass);
    }
    
    public <T> T get(String url,
                     Convert convert,
                     Class<T> responseClass) {
        return get(url, Maps.newHashMap(), convert, responseClass);
    }
    
    public <T> T post(String url,
                      Map<String, String> headers,
                      String requestBody,
                      Convert convert,
                      Class<T> responseClass) {
        String responseBody = httpClient.post(url, headers, requestBody);
        return convert.convert(responseBody, responseClass);
    }
    
    public <T> T post(String url,
                      String requestBody,
                      Convert convert,
                      Class<T> responseClass) {
        return post(url, Maps.newHashMap(), requestBody, convert, responseClass);
    }
}
