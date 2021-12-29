package in.hocg.payment.complete.channel;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/12/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class TradeOrder {
    /**
     * 外部单号
     */
    private String outOrderNo;
    /**
     * 标题
     */
    private String subject;
    /**
     * 支付金额
     */
    private BigDecimal amount;
    /**
     * 扩展信息
     */
    private String extInfos;
}
