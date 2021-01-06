package in.hocg.payment.utils;

import cn.hutool.core.convert.Convert;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by hocgin on 2019/12/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ObjectMeta {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface Alias {
        String value() default "";
    }

    private final static Map<Class<?>, ObjectMeta> CACHED = Maps.newHashMap();

    @Getter
    private final Map<String, Field> fieldMap = new HashMap<>();

    @Getter
    private final Class<?> clazz;

    private ObjectMeta(Class<?> clazz) {
        this.clazz = clazz;
        ClassUtils.getAllField(clazz).forEach(field -> {
            String filedName = field.getName();
            if (field.isAnnotationPresent(Alias.class)) {
                final Alias alias = field.getAnnotation(Alias.class);
                final String value = alias.value();
                if (!Strings.isNullOrEmpty(value)) {
                    filedName = value;
                }
            }
            fieldMap.put(filedName, field);
        });
    }

    public static ObjectMeta from(Class<?> clazz) {
        return CACHED.computeIfAbsent(clazz, ObjectMeta::new);
    }

    /**
     * 设置值
     *
     * @param target
     * @param key
     * @param val
     * @return
     */
    public void setIfExist(Object target, String key, Object val) {
        try {
            Field field = fieldMap.get(key);
            if (Objects.isNull(field)) {
                return;
            }
            field.setAccessible(true);
            field.set(target, Convert.convert(field.getType(), val));
        } catch (IllegalAccessException ignored) {
        }
    }

    /**
     * 获取值
     *
     * @param target
     * @param key
     * @param <R>
     * @return
     */
    public <R> Optional<R> get(Object target, String key) {
        Field field = fieldMap.get(key);
        if (Objects.isNull(field)) {
            return Optional.empty();
        }
        field.setAccessible(true);
        try {
            return (Optional<R>) Optional.ofNullable(field.get(target));
        } catch (IllegalAccessException ignored) {
            return Optional.empty();
        }
    }
}
