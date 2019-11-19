package in.hocg.payment.core;

import in.hocg.payment.core.storage.ConfigStorage;
import lombok.NonNull;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ConfigStorages {
    
    public static <T extends ConfigStorage> T createConfigStorage(@NonNull Class<T> configStorageClass) {
        try {
            return configStorageClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
