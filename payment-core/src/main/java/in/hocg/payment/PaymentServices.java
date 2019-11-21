package in.hocg.payment;

import in.hocg.payment.core.Help;
import in.hocg.payment.core.PaymentService;
import in.hocg.payment.core.ConfigStorage;
import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PaymentServices implements Help {
    
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
            e.printStackTrace();
        }
        return null;
    }
}
