package in.hocg.payment.core;

import in.hocg.payment.core.service.PaymentService;
import in.hocg.payment.core.storage.ConfigStorage;
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
