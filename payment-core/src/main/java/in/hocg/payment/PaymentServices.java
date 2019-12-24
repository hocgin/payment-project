package in.hocg.payment;

import in.hocg.payment.exception.ExceptionFactory;
import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PaymentServices {
    
    /**
     * 构建一个支付服务
     *
     * @param paymentServiceClass
     * @param configStorage
     * @param <T>
     * @param <C>
     * @return
     */
    public static <T extends PaymentService, C extends ConfigStorage> T createPaymentService(@NonNull Class<T> paymentServiceClass,
                                                                                             @NonNull C configStorage) {
        try {
            Constructor<T> constructor = paymentServiceClass.getConstructor(configStorage.getClass());
            return constructor.newInstance(configStorage);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw ExceptionFactory.wrap("创建支付服务失败", e);
        }
    }
}
