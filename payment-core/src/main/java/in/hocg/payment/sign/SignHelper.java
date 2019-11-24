package in.hocg.payment.sign;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import in.hocg.payment.utils.ClassUtils;
import in.hocg.payment.utils.ObjectUtils;
import lombok.NonNull;
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
public class SignHelper {
    
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
                    if (!field.isAnnotationPresent(SignField.class)) {
                        return false;
                    }
                    SignField annotation = field.getAnnotation(SignField.class);
                    return !annotation.ignore();
                }).forEach(field -> {
            SignField annotation = field.getAnnotation(SignField.class);
            String signFieldName = annotation.value();
            if (Strings.isNullOrEmpty(signFieldName)) {
                signFieldName = field.getName();
            }
            result.put(signFieldName, field);
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
        fields.keySet().forEach(item -> {
            Field field = fields.get(item);
            Object fieldValue = ObjectUtils.tryCallGetter(object, field.getName(), CaseFormat.LOWER_CAMEL);
            params.put(item, fieldValue);
        });
        return params;
    }
    
}
