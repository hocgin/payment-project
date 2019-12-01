package in.hocg.payment.core;

import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.core.data.TestConfigStorage;
import in.hocg.payment.core.data.TestPaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class BaseTests {
    
    @Test
    void testCreate() {
        TestConfigStorage configStorage = ConfigStorages.createConfigStorage(TestConfigStorage.class);
        Assertions.assertNotNull(configStorage);
        
        TestPaymentService paymentService = PaymentServices.createPaymentService(TestPaymentService.class, configStorage);
        Assertions.assertNotNull(paymentService);
    }
    
}
