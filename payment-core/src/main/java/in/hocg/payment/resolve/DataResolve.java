package in.hocg.payment.resolve;

import com.google.common.collect.Maps;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.utils.ClassUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 * 数据转换器
 *
 * @author hocgin
 */
public abstract class DataResolve<S, K> {

    @RequiredArgsConstructor
    @Getter
    public static class Rule<S, T, R> {
        @NonNull
        private final Convert<S, T> convert;
        @NonNull
        private final BiFunction<? extends T, Map<String, Object>, R> handle;
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
    public DataResolve<S, K> addRule(K key, Rule rule) {
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
    public <T> T resolve(K key, S body) {
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
     * @param args
     * @param <T>
     * @return
     */
    public <T> T handle(K key, S body, Map<String, Object> args) {
        Rule rule = rules.get(key);
        final BiFunction handle = rule.getHandle();
        if (Objects.isNull(handle)) {
            throw new UnsupportedOperationException("未设置处理方法");
        }
        return (T) handle.apply(resolve(key, body), args);
    }

    public <T> T handle(K key, S body) {
        return this.handle(key, body, Collections.emptyMap());
    }
}
