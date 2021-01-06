package in.hocg.payment.chinaums.convert;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import in.hocg.payment.chinaums.v4_8.message.ChinaUmsPayMessage;
import in.hocg.payment.chinaums.v4_8.response.ChinaUmsPayResponse;
import in.hocg.payment.convert.StringConvert;
import in.hocg.payment.utils.ObjectMeta;

import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ChinaUmsConverts {

    public static final StringConvert MESSAGE = new StringConvert<ChinaUmsPayMessage>() {

        @Override
        public <R extends ChinaUmsPayMessage> R convert(String body, Class<R> clazz) {
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
    public static final StringConvert JSON_CONVERT = (StringConvert<ChinaUmsPayResponse>) JSON::parseObject;

}
