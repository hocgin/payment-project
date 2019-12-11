package in.hocg.payment.alipay.v2.request;

import in.hocg.payment.alipay.v2.response.TradeOrderSettleResponse;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeOrderSettleRequest extends AliPayRequest<TradeOrderSettleResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.order.settle";
    
    @Override
    protected TradeOrderSettleResponse request() {
        return request(TradeOrderSettleResponse.class);
    }
    
}
