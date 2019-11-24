package in.hocg.payment;

import in.hocg.payment.core.Help;
import in.hocg.payment.core.ConfigStorage;
import lombok.NonNull;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ConfigStorages implements Help {
    
    /**
     * 构建一个配置服务
     *
     * @param configStorageClass
     * @param <T>
     * @return
     */
    public static <T extends ConfigStorage> T createConfigStorage(@NonNull Class<T> configStorageClass) {
        try {
            return configStorageClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw PaymentException.wrap(e);
        }
    }
    
}
