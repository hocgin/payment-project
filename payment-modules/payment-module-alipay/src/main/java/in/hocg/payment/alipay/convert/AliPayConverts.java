package in.hocg.payment.alipay.convert;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Maps;
import in.hocg.payment.alipay.v2.message.AliPayMessage;
import in.hocg.payment.alipay.v2.response.AliPayHttpResponse;
import in.hocg.payment.alipay.v2.response.AliPayResponse;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.utils.ObjectMeta;

import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/10.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public final class AliPayConverts {
    public static final Convert<AliPayHttpResponse> JSON = new Convert<AliPayHttpResponse>() {
        
        @Override
        public <R extends AliPayHttpResponse> R convert(String body, Class<R> clazz) {
            final Map<String, String> map = com.alibaba.fastjson.JSON.parseObject(body, new TypeReference<Map<String, String>>() {
            }, Feature.OrderedField);
            String responseKey = "";
            for (String key : map.keySet()) {
                if (key.endsWith("response")) {
                    responseKey = key;
                    break;
                }
            }
            final String responseBody = map.getOrDefault(responseKey, "{}");
            
            final R object = com.alibaba.fastjson.JSON.parseObject(responseBody, clazz);
            object.setSign(map.getOrDefault(AliPayHttpResponse.FIELD_SIGN, null));
            return object;
        }
    };
    
    public static final Convert<AliPayResponse> TEXT = new Convert<AliPayResponse>() {
        @Override
        public <R extends AliPayResponse> R convert(String body, Class<R> clazz) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    };
    
    public static final Convert<AliPayMessage> MESSAGE = new Convert<AliPayMessage>() {
        @Override
        public <R extends AliPayMessage> R convert(String body, Class<R> clazz) {
            try {
                final R result = clazz.newInstance();
                final String[] items = body.split("&");
                Map<String, String> params = Maps.newHashMap();
                String[] vars;
                for (String item : items) {
                    vars = item.split("=", 2);
                    final String key = vars[0];
                    String value = vars[1];
                    if (!"sign".equalsIgnoreCase(key)) {
                        value = URLDecoder.decode(value);
                    }
                    params.put(key, value);
                }
    
                final ObjectMeta objectMeta = ObjectMeta.from(clazz);
                params.forEach((key, value) -> {
                    objectMeta.setIfExist(result, key, value);
                });
    
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    };
}
