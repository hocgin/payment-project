package in.hocg.payment.wxpay.v1.request;

import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.wxpay.v1.WxPayConfigStorage;
import in.hocg.payment.wxpay.v1.WxPayService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class WxPayServiceTests {
    static WxPayConfigStorage configStorage;
    static WxPayService paymentService;
    
    @BeforeAll
    static void before() {
        configStorage = ConfigStorages.createConfigStorage(WxPayConfigStorage.class);
        configStorage.setAppId("appid");
        configStorage.setKey("key");
        configStorage.setMchId("MchId");
        paymentService = PaymentServices.createPaymentService(WxPayService.class, configStorage);
    }
    
    @Test
    void testUnifiedOrderRequest() {
        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setAppId("11");
        request.setAttach("aa");
        paymentService.request(request);
    }
}