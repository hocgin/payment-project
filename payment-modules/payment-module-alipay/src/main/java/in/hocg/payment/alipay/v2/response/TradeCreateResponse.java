package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeCreateResponse extends AliPayResponse {
    
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "trade_no")
    private String tradeNo;
    
}
