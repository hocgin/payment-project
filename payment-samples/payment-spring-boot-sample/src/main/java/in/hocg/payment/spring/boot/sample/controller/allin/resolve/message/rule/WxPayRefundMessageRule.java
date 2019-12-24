package in.hocg.payment.spring.boot.sample.controller.allin.resolve.message.rule;

import in.hocg.payment.convert.Convert;
import in.hocg.payment.core.DataResolve;
import in.hocg.payment.wxpay.v1.WxPayService;
import in.hocg.payment.wxpay.v1.message.PayRefundMessage;

import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WxPayRefundMessageRule extends DataResolve.Rule<PayRefundMessage, PayRefundMessage.Result> {
    public WxPayRefundMessageRule(WxPayService payService) {
        super(new Convert<PayRefundMessage>() {
            @Override
            public <R extends PayRefundMessage> R convert(String body, Class<R> clazz) {
                return payService.message(body, clazz);
            }
        }, new Function<PayRefundMessage, PayRefundMessage.Result>() {
            @Override
            public PayRefundMessage.Result apply(PayRefundMessage wxPayMessage) {
                // ..省略业务操作
                return PayRefundMessage.Result.builder()
                        .returnMsg("SUCCESS")
                        .returnCode("OK")
                        .build();
            }
        });
        
    }
}
