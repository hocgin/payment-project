package in.hocg.payment.wxpay.v1.request;

import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.core.MessageResolve;
import in.hocg.payment.core.PaymentMessage;
import in.hocg.payment.wxpay.v1.WxPayConfigStorage;
import in.hocg.payment.wxpay.v1.WxPayService;
import in.hocg.payment.wxpay.v1.message.UnifiedOrderMessage;
import in.hocg.payment.wxpay.v1.response.UnifiedOrderResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

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
        configStorage.setIsDev(true);
        paymentService = PaymentServices.createPaymentService(WxPayService.class, configStorage);
    }
    
    @Test
    void testUnifiedOrderRequest() {
        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setAttach("aa");
        UnifiedOrderResponse response = paymentService.request(request);
        System.out.println(response);
    }
    
    @Test
    void test() {
        class WxPayMessageResolve extends MessageResolve<Integer>{}
        @RequiredArgsConstructor
        class RefundResult implements PaymentMessage.Result {
            private final Object body;
        }
    
        final WxPayMessageResolve messageResolve = new WxPayMessageResolve();
        final MessageResolve.Rule<PaymentMessage, RefundResult> rule = new MessageResolve.Rule<>(new Convert<PaymentMessage>() {
            @Override
            public <R extends PaymentMessage> R convert(String body, Class<R> clazz) {
                final UnifiedOrderMessage message = new UnifiedOrderMessage();
                message.setAttach(body);
                return (R) message;
            }
        }, new Function<UnifiedOrderMessage, RefundResult>() {
            @Override
            public RefundResult apply(UnifiedOrderMessage paymentMessage) {
                return new RefundResult(paymentMessage);
            }
        });
        messageResolve.addRule(1, rule);
    
        RefundResult handle = messageResolve.handle(1, "{}");
        System.out.println(handle);
    }
}