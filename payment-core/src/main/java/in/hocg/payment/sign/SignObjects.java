package in.hocg.payment.sign;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import in.hocg.payment.utils.ClassUtils;
import in.hocg.payment.utils.ObjectUtils;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class SignObjects {
    
    private static final Map<String, Map<String, Field>> CACHE_SIGN_FIELD = Maps.newHashMap();
    
    /**
     * 获取需要签名的字段
     * - 优先缓存
     *
     * @param aClass
     * @return
     */
    public static Map<String, Field> getSignFieldsUseCache(Class<?> aClass) {
        return CACHE_SIGN_FIELD.computeIfAbsent(aClass.getName(), (key) -> getSignFields(aClass));
    }
    
    /**
     * 获取需要签名的字段
     *
     * @param aClass
     * @return
     */
    public static Map<String, Field> getSignFields(Class<?> aClass) {
        Map<String, Field> result = Maps.newHashMap();
        ClassUtils.from(aClass).getAllField()
                .stream()
                .filter(field -> {
                    if (!field.isAnnotationPresent(ApiField.class)) {
                        return false;
                    }
                    ApiField annotation = field.getAnnotation(ApiField.class);
                    return !annotation.ignore();
                }).forEach(field -> {
            ApiField annotation = field.getAnnotation(ApiField.class);
            String fieldName = annotation.value();
            if (Strings.isNullOrEmpty(fieldName)) {
                fieldName = field.getName();
            }
            result.put(fieldName, field);
        });
        
        return result;
    }
    
    /**
     * 获取要签名的值
     *
     * @param object
     * @return
     */
    public static Map<String, Object> getSignValues(@NonNull Object object) {
        Class<?> aClass = object.getClass();
        Map<String, Field> fields = getSignFieldsUseCache(aClass);
        Map<String, Object> params = Maps.newHashMap();
        fields.forEach((key, value) -> {
            Object fieldValue = ObjectUtils.tryCallGetter(object, value.getName(), CaseFormat.LOWER_CAMEL);
            params.put(key, fieldValue);
        });
        return params;
    }
    
}
