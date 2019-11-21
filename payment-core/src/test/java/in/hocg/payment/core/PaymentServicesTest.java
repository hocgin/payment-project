package in.hocg.payment.core;

import com.sun.tools.javac.util.Assert;
import in.hocg.payment.core.request.AliPayRequest;
import in.hocg.payment.core.response.AliPayResponse;
import in.hocg.payment.core.service.AliPayPaymentService;
import in.hocg.payment.core.storage.AliPayConfigStorage;
import in.hocg.payment.core.storage.ConfigStorage;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class PaymentServicesTest {
    
    @Test
    void createPaymentService() {
        ConfigStorage configStorage = ConfigStorages.createConfigStorage(AliPayConfigStorage.class);
        AliPayPaymentService paymentService = PaymentServices.createPaymentService(AliPayPaymentService.class, configStorage);
        Assert.checkNonNull(paymentService);
    }
    
    @Test
    void testJson() {
        AliPayRequest request = new AliPayRequest().fromJson("{\"id\": 1 }");
        Assert.checkNonNull(request);
    }
    
    @Test
    void test() {
        ConfigStorage configStorage = ConfigStorages.createConfigStorage(AliPayConfigStorage.class);
        AliPayPaymentService paymentService = PaymentServices.createPaymentService(AliPayPaymentService.class, configStorage);
        AliPayRequest request = new AliPayRequest();
        AliPayResponse response = paymentService.request(request);
        System.out.println(response);
    }
}