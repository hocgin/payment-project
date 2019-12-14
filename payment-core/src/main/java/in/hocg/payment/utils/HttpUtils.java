package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class HttpUtils {
    
    public static String getUrl(String url, Map<String, Object> params) {
        String mapString = StringUtils.mapToString(params, "&");
        return String.format("%s?%s", url, mapString);
    }
}
