package in.hocg.payment.alipay.convert;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import in.hocg.payment.alipay.v2.response.AliPayResponse;
import in.hocg.payment.convert.Convert;

import java.util.Map;

/**
 * Created by hocgin on 2019/12/10.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum AliPayConverts implements Convert<AliPayResponse> {
    JSON {
        @Override
        public <T extends AliPayResponse> T convert(String body, Class<T> clazz) {
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
            
            final T object = com.alibaba.fastjson.JSON.parseObject(responseBody, clazz);
            object.setSign(map.getOrDefault(AliPayResponse.FIELD_SIGN, null));
            return object;
        }
    }
}
