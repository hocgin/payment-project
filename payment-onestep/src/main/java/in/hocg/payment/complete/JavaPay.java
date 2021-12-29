package in.hocg.payment.complete;

import in.hocg.payment.complete.channel.PayChannel;
import in.hocg.payment.complete.config.PayConfigManager;

/**
 * Created by hocgin on 2021/12/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class JavaPay {
    public static PayConfigManager CONFIG_STORAGE_MANAGER;

    public static void mount(PayConfigManager configStorageManager) {
        CONFIG_STORAGE_MANAGER = configStorageManager;
    }

    public static PayChannel use(String channelId) {
        return CONFIG_STORAGE_MANAGER.load(channelId).getPayChannel();
    }

}
