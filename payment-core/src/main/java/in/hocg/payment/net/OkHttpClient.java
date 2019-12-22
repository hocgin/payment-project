package in.hocg.payment.net;

import com.google.common.collect.Maps;
import in.hocg.payment.core.ErrorContext;
import in.hocg.payment.exception.ExceptionFactory;
import in.hocg.payment.exception.NetworkException;
import in.hocg.payment.utils.LangUtils;
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
    public Response execGet(String url, Map<String, String> headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .get()
                .build();
        try {
            return CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new NetworkException("HTTP:发起请求失败");
        }
    }
    
    /**
     * POST 请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @return
     */
    public Response execPost(String url, Map<String, String> headers, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(requestBody)
                .build();
        try {
            return CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw ExceptionFactory.wrap("", e);
        }
    }
    
    
    @Override
    public String get(String url, Map<String, String> headers) {
        Response response = execGet(url, headers);
        if (!response.isSuccessful()) {
            throw new NetworkException("HTTP:响应状态码非[200..300)");
        }
        ResponseBody responseBody = response.body();
        try {
            assert responseBody != null;
            return responseBody.string();
        } catch (IOException e) {
            throw new NetworkException("HTTP:获取响应数据失败");
        }
    }
    
    @Override
    public String get(String url) {
        ErrorContext.instance().activity("发起请求").url(url);
        return get(url, Maps.newHashMap());
    }
    
    @Override
    public String post(String url, Map<String, String> headers, String body) {
        body = LangUtils.getOrDefault(body, "");
        RequestBody requestBody = RequestBody.create(body.getBytes());
        Response response = execPost(url, headers, requestBody);
        if (!response.isSuccessful()) {
            throw new NetworkException("HTTP:响应状态码非[200..300)");
        }
        ResponseBody responseBody = response.body();
        try {
            assert responseBody != null;
            return responseBody.string();
        } catch (IOException e) {
            throw new NetworkException("HTTP:获取响应数据失败");
        }
    }
    
    @Override
    public String post(String url, String body) {
        return post(url, Maps.newHashMap(), body);
    }
    
}
