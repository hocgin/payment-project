package in.hocg.payment.complete.message;

import in.hocg.payment.complete.channel.TradeOrder;

import java.util.function.Consumer;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Message {

    void payResult(Consumer<TradeOrder> handle);

    void refundResult(Consumer<TradeOrder> handle);
}
