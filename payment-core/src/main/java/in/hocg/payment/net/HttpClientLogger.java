package in.hocg.payment.net;

import com.google.common.collect.Maps;
import in.hocg.payment.core.ErrorContext;
import in.hocg.payment.utils.LangUtils;
import in.hocg.payment.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.Proxy;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by hocgin on 2019/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class HttpClientLogger implements HttpClient {
    private HttpClient client;
    
    public HttpClientLogger(HttpClient client) {
        this.client = client;
    }
    
    @Override
    public HttpClient proxy(Proxy proxy) {
        ErrorContext.instance().activity("设置HTTP代理");
        client.proxy(proxy);
        return this;
    }
    
    @Override
    public String get(String url, Map<String, String> headers) {
        ErrorContext.instance().activity("发起 GET 请求").url(url);
        String result = null;
        try {
            result = client.get(url, headers);
        } finally {
            debug("GET", url, headers, null, result);
        }
        return result;
    }
    
    @Override
    public String get(String url) {
        String result = null;
        try {
            result = client.get(url);
        } finally {
            debug("GET", url, null, null, result);
        }
        return result;
    }
    
    @Override
    public String post(String url, Map<String, String> headers, String body) {
        ErrorContext.instance().activity("发起 POST 请求").url(url);
        String result = null;
        try {
            result = client.post(url, headers, body);
        } finally {
            debug("POST", url, headers, body, result);
        }
        return result;
    }
    
    @Override
    public String post(String url, String body) {
        ErrorContext.instance().activity("发起 POST 请求").url(url);
        String result = null;
        try {
            result = client.post(url, body);
        } finally {
            debug("POST", url, null, body, result);
        }
        return result;
    }
    
    /**
     * ==========================================
     * GET: http://hostname/sub-url/url
     * ----------------「请求头」------------------
     * {}
     * ----------------「请求体」------------------
     * {}
     * ----------------「响应体」------------------
     * {}
     * ==========================================
     */
    private void debug(String method, String url, Map<String, String> headers, String body, String response) {
        final StringJoiner content = new StringJoiner(System.lineSeparator())
                .add("")
                .add("========================================================================================")
                .add(String.format("%s: %s", method, url))
                .add("->「请求头」:")
                .add(TextUtils.pretty(LangUtils.getOrDefault(headers, Maps.newHashMap())))
                .add("->「请求体」:")
                .add(TextUtils.pretty(LangUtils.getOrDefault(body, "")))
                .add("->「响应体」:")
                .add(TextUtils.pretty(LangUtils.getOrDefault(response, "")))
                .add("========================================================================================");
        log.debug("{}", content);
    }
}
