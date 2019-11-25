package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.sign.SignField;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class TradePayResponse extends AliPayResponse {
    
    @JSONField(name = "out_trade_no")
    @SignField(value = "out_trade_no", required = true)
    private String outTradeNo;
    
    @JSONField(name = "trade_no")
    @SignField(value = "trade_no", required = true)
    private String tradeNo;
    
    @JSONField(name = "total_amount")
    @SignField(value = "total_amount", required = true)
    private String totalAmount;
    
    @JSONField(name = "seller_id")
    @SignField(value = "seller_id", required = true)
    private String sellerId;
    
    @JSONField(name = "merchant_order_no")
    @SignField(value = "merchant_order_no", required = true)
    private String merchantOrderNo;
    
}
