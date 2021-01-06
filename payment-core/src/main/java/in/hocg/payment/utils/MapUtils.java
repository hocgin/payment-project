package in.hocg.payment.utils;

import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.net.URLDecoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class MapUtils {

    /**
     * 对 Map 的 Key 进行排序
     *
     * @param data
     * @return
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortedUseKey(Map<K, V> data, boolean asc) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> stream = data.entrySet().stream();
        if (asc) {
            stream.sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            stream.sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

    /**
     * 对 Map 的 Value 进行排序
     *
     * @param data
     * @param asc
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortedUseValue(Map<K, V> data, boolean asc) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> stream = data.entrySet().stream();
        if (asc) {
            stream.sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            stream.sorted(Map.Entry.<K, V>comparingByValue().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

    public static Map<String, Object> getValues(String body) {
        return getValues(body, Collections.emptyList());
    }

    public static Map<String, Object> getValues(String body, List<String> ignoreKeys) {
        Map<String, Object> values = Maps.newHashMap();
        final String[] items = body.split("&");
        String[] vars;
        for (String item : items) {
            vars = item.split("=", 2);
            final String key = vars[0];
            if (ignoreKeys.contains(key)) {
                continue;
            }
            values.put(key, URLDecoder.decode(vars[1]));
        }
        return values;
    }
}
