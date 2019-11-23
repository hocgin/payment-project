package in.hocg.payment.sign;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import in.hocg.payment.utils.ClassUtils;
import in.hocg.payment.utils.ObjectUtils;
import in.hocg.payment.utils.SignUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class SignHelper {
    @JSONField
    private static final Map<String, Map<String, Field>> CACHE_SIGN_FIELD = Maps.newHashMap();
    
    /**
     * 获取需要签名的字段
     * - 优先缓存
     *
     * @param aClass
     * @return
     */
    private static Map<String, Field> getSignFieldsUseCache(Class<?> aClass) {
        return CACHE_SIGN_FIELD.computeIfAbsent(aClass.getName(), (key) -> getSignFields(aClass));
    }
    
    /**
     * 获取需要签名的字段
     *
     * @param aClass
     * @return
     */
    private static Map<String, Field> getSignFields(Class<?> aClass) {
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
    private static SortedMap<String, String> getSignValues(@NonNull Object object) {
        Class<?> aClass = object.getClass();
        Map<String, Field> fields = getSignFieldsUseCache(aClass);
        Set<String> signFieldNames = fields.keySet();
        SortedMap<String, String> sortedMap = Maps.newTreeMap();
        signFieldNames.forEach(item -> {
            Object fieldValue = ObjectUtils.tryCallGetter(object, fields.get(item).getName(), CaseFormat.LOWER_CAMEL);
            if (Objects.nonNull(fieldValue)) {
                sortedMap.put(item, String.valueOf(fieldValue));
            }
        });
        return sortedMap;
    }
    
    /**
     * 根据对象获取签名字符串
     *
     * @param object
     * @return
     */
    public static String getSignString(@NonNull Object object,
                                       @NonNull String privateKey,
                                       @NonNull SignType signType) {
        SortedMap<String, String> signValues = getSignValues(object);
        String waitSignString = SignUtils.getSignString(signValues);
        return signType.sign(waitSignString, privateKey);
    }
    
    /**
     * 验证签名
     *
     * @param object
     * @param publicKey
     * @param signType
     * @param signValue
     * @return
     */
    public static boolean verifySign(@NonNull Object object,
                                     @NonNull String publicKey,
                                     @NonNull SignType signType,
                                     @NonNull String signValue) {
        SortedMap<String, String> signValues = getSignValues(object);
        String waitSignString = SignUtils.getSignString(signValues);
        return signType.verify(waitSignString, publicKey, signValue);
    }
    
}
