package in.hocg.payment.complete.core;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RefundQueryResult {
    private String tradeNo;
    private String outTradeNo;
    private String totalAmount;
    private String refundAmount;

}
