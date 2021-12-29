package in.hocg.payment.complete.channel;

import in.hocg.payment.complete.channel.option.PayOption;
import in.hocg.payment.complete.channel.vo.PayResult;
import in.hocg.payment.complete.channel.vo.TradeOrderResult;
import in.hocg.payment.complete.message.Message;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2021/12/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PayChannel {

    /**
     * 支付
     *
     * @param order
     * @param option
     * @param <T>
     */
    PayResult pay(TradeOrder order, PayOption option);

    /**
     * 支付
     *
     * @param order
     */
    default void pay(TradeOrder order) {
        pay(order, null);
    }

    /**
     * 关单
     *
     * @param outOrderNo
     */
    void close(String outOrderNo);

    /**
     * 退款
     *
     * @param outOrderNo
     * @param refundAmt
     */
    void refund(String outOrderNo, BigDecimal refundAmt);

    /**
     * 查询交易单
     *
     * @param outOrderNo
     * @return
     */
    TradeOrderResult query(String outOrderNo);

    /**
     * 查流水
     *
     * @return
     */
    List<BillData> queryBill();

    /**
     * 消息
     *
     * @param msg
     * @return
     */
    Message message(String msg);
}
