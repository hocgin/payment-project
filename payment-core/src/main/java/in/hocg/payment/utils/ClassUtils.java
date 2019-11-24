package in.hocg.payment.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by hocgin on 2019/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ClassUtils {
    /**
     * 缓存已分析的类
     */
    private static Map<Class, ClassUtils> CACHED = Maps.newHashMap();
    private final Class<?> clazz;
    
    private ClassUtils(Class<?> clazz) {
        this.clazz = clazz;
    }
    
    public static ClassUtils from(Class<?> clazz) {
        return CACHED.computeIfAbsent(clazz, ClassUtils::new);
    }
    
    /**
     * 获取所有函数
     *
     * @return
     */
    public ArrayList<Method> getAllMethod() {
        ArrayList<Method> result = Lists.newArrayList();
        result.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        Class<?> superclass = clazz.getSuperclass();
        if (Object.class.equals(superclass)) {
            return result;
        }
        result.addAll(ClassUtils.from(superclass).getAllMethod());
        
        return result;
    }
    
    /**
     * 获取所有字段
     *
     * @return
     */
    public List<Field> getAllField() {
        ArrayList<Field> result = Lists.newArrayList();
        result.addAll(Arrays.asList(clazz.getDeclaredFields()));
        
        Class<?> superclass = clazz.getSuperclass();
        if (Object.class.equals(superclass)) {
            return result;
        }
        result.addAll(ClassUtils.from(superclass).getAllField());
        return result;
    }
    
    /**
     * 查找字段
     *
     * @param fieldName
     * @return
     */
    public Field getField(String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = clazz.getSuperclass();
            if (!Object.class.equals(superclass)) {
                return ClassUtils.from(superclass).getField(fieldName);
            }
        }
        if (Objects.isNull(field)) {
            throw new IllegalArgumentException(String.format("在 %s 中未找到 %s 字段", clazz.getSimpleName(), fieldName));
        }
        return field;
    }
    
    /**
     * 通过函数名称获取 Class 对应的函数
     *
     * @param methodName
     * @return
     */
    public Method getMethod(String methodName) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        
        throw new IllegalArgumentException(clazz + "未找到函数名为 " + methodName + " 的函数");
    }
    
    /**
     * 检查是否是基础类型
     * Integer => true
     * Long => true
     *
     * @param clazz
     * @return
     */
    public static boolean isPrimitive(Class<?> clazz) {
        try {
            return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return false;
        }
    }
    
    /**
     * 判断是否基础类型
     *
     * @param clazz
     * @return
     */
    public static boolean isBaseType(Class clazz) {
        return clazz.equals(Integer.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(Boolean.class);
    }
    
    /**
     * 判断是否数组
     *
     * @param clazz
     * @return
     */
    public static boolean isArray(Class clazz) {
        return clazz.isArray();
    }
}
