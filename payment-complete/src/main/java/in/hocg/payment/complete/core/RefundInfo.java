package in.hocg.payment.complete.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RefundInfo {
    /**
     * [必须] 商户单号
     */
    private String outTradeSn;
    /**
     * 商户退款单号
     */
    private String outRefundSn;
    /**
     * [必须] 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 通知地址
     */
    private String notifyUrl;
}
