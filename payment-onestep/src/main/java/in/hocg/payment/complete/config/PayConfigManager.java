package in.hocg.payment.complete.config;

/**
 * Created by hocgin on 2021/12/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PayConfigManager {

    /**
     * 获取配置
     *
     * @param channelId
     * @return
     */
    PayConfig load(String channelId);
}
