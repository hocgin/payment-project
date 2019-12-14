package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeCancelResponse extends AliPayHttpResponse {
    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "retry_flag")
    private String retryFlag;
    @JSONField(name = "action")
    private String action;
    @JSONField(name = "gmt_refund_pay")
    private String gmtRefundPay;
    @JSONField(name = "refund_settlement_id")
    private String refundSettlementId;
}
