package in.hocg.payment.complete.channel;

import in.hocg.payment.complete.config.PayConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class AbsPayChannel<T extends PayConfig> implements PayChannel {
    @Getter
    @Setter
    private T config;
}
