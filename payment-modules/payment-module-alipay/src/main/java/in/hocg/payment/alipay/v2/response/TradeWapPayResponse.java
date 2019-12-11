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
public class TradeWapPayResponse extends AliPayResponse {
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "total_amount")
    private String totalAmount;
    @JSONField(name = "seller_id")
    private String sellerId;
    @JSONField(name = "merchant_order_no")
    private String merchantOrderNo;
}