package in.hocg.payment.core;

import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.core.data.TestConfigStorage;
import in.hocg.payment.core.data.TestPaymentService;
import in.hocg.payment.core.data.TestRequest;
import in.hocg.payment.core.data.TestResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class RequestTests {
    static TestConfigStorage configStorage;
    static TestPaymentService paymentService;
    
    @BeforeAll
    static void before() {
        configStorage = ConfigStorages.createConfigStorage(TestConfigStorage.class);
        paymentService = PaymentServices.createPaymentService(TestPaymentService.class, configStorage);
    }
    
    @Test
    void testTestRequest() {
        TestRequest request = new TestRequest();
        TestResponse response = paymentService.request(request);
    }
    
}
