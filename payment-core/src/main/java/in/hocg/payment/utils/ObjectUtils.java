package in.hocg.payment.utils;

import com.google.common.base.CaseFormat;
import com.google.common.reflect.Invokable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ObjectUtils {
    
    /**
     * 调用指定函数
     *
     * @param target
     * @param methodName
     * @param args
     * @param <R>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <R> R callMethod(Object target, String methodName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = target.getClass().getMethod(methodName);
        Invokable<Object, R> from = (Invokable<Object, R>) Invokable.from(method);
        return from.invoke(target, args);
    }
    
    /**
     * 调用 Getter
     *
     * @param target
     * @param fieldName
     * @param <R>
     * @return
     */
    public static <R> R callGetter(Object target, String fieldName, CaseFormat fieldCaseFormat) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String methodName = "get" + fieldCaseFormat.to(CaseFormat.UPPER_CAMEL, fieldName);
        return callMethod(target, methodName);
    }
    
    
    public static <R> R tryCallGetter(Object target, String fieldName, CaseFormat fieldCaseFormat) {
        try {
            return callGetter(target, fieldName, fieldCaseFormat);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
