package in.hocg.payment.net;

import com.google.common.collect.Maps;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.utils.JSONUtils;
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
        print("GET", url, headers, "{}", responseBody);
        return (T) convert.convert(responseBody, responseClass);
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
        print("POST", url, headers, requestBody, responseBody);
        return (T) convert.convert(responseBody, responseClass);
    }
    
    public <T> T post(String url,
                      String requestBody,
                      Convert convert,
                      Class<T> responseClass) {
        return post(url, Maps.newHashMap(), requestBody, convert, responseClass);
    }
    
    /**
     * 打印日志
     *
     * @param method
     * @param url
     * @param headers
     * @param requestBody
     * @param responseBody
     */
    private void print(String method, String url, Map<String, String> headers, String requestBody, String responseBody) {
        log.info("{}: {}\nHeaders: {}\nRequest Body: {}\nResponse Body: {}", method, url, headers, JSONUtils.pretty(requestBody), JSONUtils.pretty(responseBody));
    }
}
