package in.hocg.payment.spring.boot.sample.controller.allin.resolve;

import in.hocg.payment.convert.Convert;
import in.hocg.payment.core.MessageResolve;
import in.hocg.payment.wxpay.v1.message.UnifiedOrderMessage;
import in.hocg.payment.wxpay.v1.message.WxPayMessage;
import lombok.NonNull;

import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WxMessageRule extends MessageResolve.Rule<WxPayMessage, WxPayMessage.Result> {
    public WxMessageRule() {
        this(new Convert<WxPayMessage>() {
            @Override
            public <R extends WxPayMessage> R convert(String body, Class<R> clazz) {
                final UnifiedOrderMessage message = new UnifiedOrderMessage();
                message.setAttach(body);
                return (R) message;
            }
        }, new Function<UnifiedOrderMessage, WxPayMessage.Result>() {
            @Override
            public WxPayMessage.Result apply(UnifiedOrderMessage wxPayMessage) {
                return UnifiedOrderMessage.Result.builder()
                        .returnMsg("msg")
                        .returnCode("code")
                        .build();
            }
        });
        
    }
    
    public WxMessageRule(@NonNull Convert<WxPayMessage> convert,
                         Function<? extends WxPayMessage, WxPayMessage.Result> handle) {
        super(convert, handle);
    }
}
