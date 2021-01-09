package in.hocg.payment.complete.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class TradeQueryResult {
    /**
     * 第三方单号
     */
    private String tradeSn;
    /**
     * 商户单号
     */
    private String outTradeSn;
    /**
     * 是否支付成功
     */
    private TradeStatus tradeStatus;
    /**
     * 用户支付的金额
     */
    private BigDecimal payAmount;
    /**
     * 发起交易的总金额
     */
    private BigDecimal totalAmount;

    public enum TradeStatus {
        // 交易创建，等待买家付款
        WAIT_BUYER_PAY,
        // 未付款交易超时关闭，或支付完成后全额退款
        TRADE_CLOSED,
        // 交易支付成功
        TRADE_SUCCESS,
        // 交易结束，不可退款
        TRADE_FINISHED;
    }
}
