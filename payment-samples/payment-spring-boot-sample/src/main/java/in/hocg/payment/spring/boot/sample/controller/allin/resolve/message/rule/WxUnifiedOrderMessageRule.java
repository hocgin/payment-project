package in.hocg.payment.spring.boot.sample.controller.allin.resolve.message.rule;

import in.hocg.payment.convert.StringConvert;
import in.hocg.payment.resolve.StringResolve;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.message.UnifiedOrderMessage;

import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WxUnifiedOrderMessageRule extends StringResolve.StringRule<UnifiedOrderMessage, UnifiedOrderMessage.Result> {
    public WxUnifiedOrderMessageRule(WxPayService payService) {
        super(new StringConvert<UnifiedOrderMessage>() {
            @Override
            public <R extends UnifiedOrderMessage> R convert(String body, Class<R> clazz) {
                return payService.message(body, clazz);
            }
        }, new Function<UnifiedOrderMessage, UnifiedOrderMessage.Result>() {
            @Override
            public UnifiedOrderMessage.Result apply(UnifiedOrderMessage wxPayMessage) {
                // ..省略业务操作
                return UnifiedOrderMessage.Result.builder()
                        .returnMsg("SUCCESS")
                        .returnCode("OK")
                        .build();
            }
        });

    }
}
