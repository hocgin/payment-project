package in.hocg.payment.complete.channel.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/12/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class TradeOrderResult {
    /**
     * 接入商户单号
     */
    private String orderNo;
    /**
     * 支付商户单号
     */
    private String outOrderNo;
    /**
     * 状态
     */
    private Status status;
    /**
     * 交易金额
     */
    private BigDecimal totalAmt;
    /**
     * 完成时间
     */
    private LocalDateTime finishAt;

    public enum Status {
        Payed,
        Waiting,
        ;
    }
}
