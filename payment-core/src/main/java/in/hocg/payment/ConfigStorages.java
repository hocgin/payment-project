package in.hocg.payment;

import in.hocg.payment.core.ConfigStorage;
import in.hocg.payment.exception.ExceptionFactory;
import lombok.NonNull;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ConfigStorages {
    
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
            throw ExceptionFactory.wrap("创建"+configStorageClass+"对象发生错误", e);
        }
    }
    
}
