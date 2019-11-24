package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

import java.util.LinkedHashMap;
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
}
