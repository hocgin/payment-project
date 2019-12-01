package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class HttpUtils {
    
    public static String getUrl(String url, Map<String, Object> params) {
        StringJoiner paramsStr = new StringJoiner("&");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            paramsStr.merge(new StringJoiner("=").add(key).add(String.valueOf(value)));
        }
        return String.format("%s?%s", url, paramsStr.toString());
    }
}
