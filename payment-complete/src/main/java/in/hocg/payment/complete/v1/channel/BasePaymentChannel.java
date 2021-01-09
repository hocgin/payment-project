package in.hocg.payment.complete.v1.channel;

import in.hocg.payment.ConfigStorage;
import lombok.Getter;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public abstract class BasePaymentChannel implements PaymentChannel {
    private final ConfigStorage configStorage;

    public BasePaymentChannel(ConfigStorage configStorage) {
        this.configStorage = configStorage;
    }


}
