package in.hocg.payment.sign.strategy.value;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ValueStrategy {
    
    /**
     * 处理
     *
     * @param entry
     * @return
     */
    default Map.Entry<String, Object> map(Map.Entry<String, Object> entry) {
        return entry;
    }
    
    /**
     * 过滤
     *
     * @param entry
     * @return
     */
    default boolean filter(Map.Entry<String, Object> entry) {
        return true;
    }
    
    /**
     * 排序
     *
     * @return
     */
    default Comparator<? super Map.Entry<String, Object>> sorted() {
        return Map.Entry.comparingByKey();
    }
    
    /**
     * 排序后处理
     *
     * @param entry
     * @return
     */
    default Map.Entry<String, Object> mapOrdered(Map.Entry<String, Object> entry) {
        return entry;
    }
}
