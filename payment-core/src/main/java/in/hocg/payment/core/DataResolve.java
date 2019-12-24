package in.hocg.payment.core;

import com.google.common.collect.Maps;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.utils.ClassUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 * 数据转换器
 *
 * @author hocgin
 */
public abstract class DataResolve<K> {
    
    @RequiredArgsConstructor
    @Getter
    public static class Rule<T, R> {
        @NonNull
        private final Convert<T> convert;
        @NonNull
        private final Function<? extends T, R> handle;
    }
    
    /**
     * 规则
     */
    private Map<K, Rule> rules = Maps.newHashMap();
    
    /**
     * 添加解析规则
     *
     * @param key
     * @param rule
     * @return
     */
    public DataResolve<K> addRule(K key, Rule rule) {
        rules.put(key, rule);
        return this;
    }
    
    /**
     * 解析消息
     *
     * @param key
     * @param body
     * @param <T>
     * @return
     */
    public <T> T resolve(K key, String body) {
        Rule rule = rules.get(key);
        @NonNull final Convert convert = rule.getConvert();
        final Class superclass = ClassUtils.getGenericSuperclass(rule.getClass(), 0);
        return ((T) convert.convert(body, superclass));
    }
    
    /**
     * 进行处理
     *
     * @param key
     * @param body
     * @param <T>
     * @return
     */
    public <T> T handle(K key, String body) {
        Rule rule = rules.get(key);
        final Function handle = rule.getHandle();
        if (Objects.isNull(handle)) {
            throw new UnsupportedOperationException("未设置处理方法");
        }
        return (T) handle.apply(resolve(key, body));
    }
}
