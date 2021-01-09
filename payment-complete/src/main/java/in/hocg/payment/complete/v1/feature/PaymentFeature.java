package in.hocg.payment.complete.v1.feature;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.PaymentService;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PaymentFeature {

    ConfigStorage getConfigStorage();

    <T extends PaymentService<?>> T getPaymentService();
}
