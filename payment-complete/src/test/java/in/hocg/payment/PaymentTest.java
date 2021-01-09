package in.hocg.payment;

import in.hocg.payment.demo.PaymentWay;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PaymentTest {

    void testPay() {
        Object result = PaymentWay.AliPay.pay("");
    }

    void testRefund() {
        Object result = PaymentWay.AliPay.refund("");
    }
}
