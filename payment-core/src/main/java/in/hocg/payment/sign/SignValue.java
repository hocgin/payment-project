package in.hocg.payment.sign;

import com.google.common.collect.Maps;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by hocgin on 2019/12/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Accessors(chain = true)
public class SignValue {

    /**
     * 签名前的值
     */
    private Map<String, Object> handledValues;

    /**
     * 排序前处理
     */
    @Setter
    private Function<Map.Entry<String, Object>, Map.Entry<String, Object>> mapStrategy;

    /**
     * 排序后处理
     */
    @Setter
    private Function<Map.Entry<String, Object>, Map.Entry<String, Object>> mapSortedStrategy;

    /**
     * 合并策略
     */
    @Setter
    private Function<Map<String, Object>, String> mergeStrategy = map -> map.keySet()
        .stream()
        .filter(fieldName -> !((String)map.get(fieldName)).isEmpty())
        .filter(fieldName -> !"sign".equalsIgnoreCase(fieldName))
        .map(fieldName -> String.format("%s=%s", fieldName, map.get(fieldName)))
        .reduce((s1, s2) -> String.format("%s&%s", s1, s2)).orElse("");

    /**
     * 过滤器
     */
    @Setter
    private Predicate<? super Map.Entry<String, Object>> filter;

    /**
     * 排序方案
     */
    @Setter
    private Comparator<? super Map.Entry<String, Object>> sorted;

    /**
     * 处理状态
     */
    private boolean isHandled = false;

    /**
     * 处理
     *
     * @return
     */
    public SignValue handle(Map<String, Object> initValues) {
        Map<String, Object> result = Maps.newLinkedHashMap();
        Stream<Map.Entry<String, Object>> stream = Maps.newHashMap(initValues)
            .entrySet().stream();
        if (Objects.nonNull(mapStrategy)) {
            stream = stream.map(mapStrategy);
        }
        if (Objects.nonNull(filter)) {
            stream = stream.filter(filter);
        }
        if (Objects.nonNull(sorted)) {
            stream = stream.sorted(sorted);
        }
        if (Objects.nonNull(mapSortedStrategy)) {
            stream = stream.map(mapSortedStrategy);
        }
        stream.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        this.handledValues = result;
        this.isHandled = true;
        return this;
    }

    /**
     * 排序策略
     *
     * @param keyOrder
     * @return
     */
    public SignValue setOrderStrategy(KeyOrder keyOrder) {
        this.sorted = keyOrder.sorted();
        return this;
    }

    /**
     * 签名
     *
     * @return
     */
    public String getSignValue() {
        if (!isHandled) {
            throw new UnsupportedOperationException("需要先调用 in.hocg.sign.SignValue#handle");
        }
        return mergeStrategy.apply(this.handledValues);
    }

    /**
     * 获取处理过程中的值
     *
     * @return
     */
    public Map<String, Object> getHandledValues() {
        if (!isHandled) {
            throw new UnsupportedOperationException("需要先调用 in.hocg.sign.SignValue#handle");
        }
        return this.handledValues;
    }

    /**
     * 排序策略
     */
    public enum KeyOrder {
        /**
         * 降序
         */
        DESC {
            @Override
            Comparator<? super Map.Entry<String, Object>> sorted() {
                return Collections.reverseOrder(Map.Entry.comparingByKey());
            }
        },
        /**
         * 升序
         */
        ASC {
            @Override
            Comparator<? super Map.Entry<String, Object>> sorted() {
                return Map.Entry.comparingByKey();
            }
        };

        abstract Comparator<? super Map.Entry<String, Object>> sorted();
    }

}
