package in.hocg.payment.spring.boot.sample.controller.allin.resolve.message.rule;

import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.message.TradeStatusSyncMessage;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.core.DataResolve;

import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AliPayTradeStatusSync extends DataResolve.Rule<TradeStatusSyncMessage, TradeStatusSyncMessage.Result> {
    public AliPayTradeStatusSync(AliPayService payService) {
        super(new Convert<TradeStatusSyncMessage>() {
            @Override
            public <R extends TradeStatusSyncMessage> R convert(String body, Class<R> clazz) {
                return payService.message(body, clazz);
            }
        }, new Function<TradeStatusSyncMessage, TradeStatusSyncMessage.Result>() {
            @Override
            public TradeStatusSyncMessage.Result apply(TradeStatusSyncMessage wxPayMessage) {
                // ..省略业务操作
                return TradeStatusSyncMessage.Result.builder()
                        .result("success")
                        .build();
            }
        });
        
    }
}
