package in.hocg.payment;

import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.complete.v1.PaymentPlatform;
import in.hocg.payment.demo.PaymentWay;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class MessageTests {

    void testPay() {
        PaymentPlatform.AliPay(ConfigStorages.createConfigStorage(AliPayConfigStorage.class))
            .AliPay().tradeAppPay(null);
    }

    void testRefund() {
        Object result = PaymentWay.AliPay.refund("");
    }
}
