package in.hocg.payment.core.data;

import in.hocg.payment.core.PaymentService;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class TestPaymentService extends PaymentService<TestConfigStorage> {
    public TestPaymentService(TestConfigStorage configStorage) {
        super(configStorage);
    }
}
