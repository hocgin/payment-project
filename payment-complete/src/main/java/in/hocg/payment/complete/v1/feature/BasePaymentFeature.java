package in.hocg.payment.complete.v1.feature;

import in.hocg.payment.ConfigStorage;
import lombok.Getter;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public abstract class BasePaymentFeature implements PaymentFeature {
    private ConfigStorage configStorage;
    public BasePaymentFeature(ConfigStorage configStorage) {
        this.configStorage = configStorage;
    }
}
