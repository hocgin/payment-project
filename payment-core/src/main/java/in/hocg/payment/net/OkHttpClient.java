package in.hocg.payment.net;

import com.google.common.collect.Maps;
import in.hocg.payment.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.net.Proxy;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class OkHttpClient implements HttpClient {
    
    private static okhttp3.OkHttpClient CLIENT = new okhttp3.OkHttpClient.Builder().build();
    
    @Override
    public HttpClient proxy(Proxy proxy) {
        CLIENT = new okhttp3.OkHttpClient.Builder()
                .proxy(proxy)
                .build();
        return this;
    }
    
    /**
     * GET 请求
     *
     * @param url
     * @param headers
     * @return
     */
    public Response get(String url, Map<String, String> headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .get()
                .build();
        try {
            return CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 请求使用 GET 方式
     *
     * @param url
     * @param headers
     * @return
     */
    @Override
    public <T> T get(String url, Map<String, String> headers, Class<T> responseClass) {
        Response response = get(url, headers);
        log.debug("GET URL={} Headers={}", url, headers);
        if (!response.isSuccessful()) {
            throw new RuntimeException("请求失败");
        }
        ResponseBody body = response.body();
        String content = null;
        if (body != null) {
            try {
                content = body.string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        T result;
        try {
            result = StringUtils.stringToBean(content, responseClass);
            log.debug("响应结果转换: 文本数据={}, 转换后的实体={}", content, result);
        } catch (Exception e) {
            log.error("响应结果转换时出现异常，期望类型为：{}，实际响应结果为：{}", responseClass, content);
            throw new RuntimeException(e);
        }
        return result;
        
    }
    
    @Override
    public <T> T get(String url, Class<T> responseClass) {
        return get(url, Maps.newHashMap(), responseClass);
    }
    
    /**
     * POST 请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @return
     */
    public Response post(String url, Map<String, String> headers, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(requestBody)
                .build();
        try {
            return CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
