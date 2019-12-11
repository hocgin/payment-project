package in.hocg.payment.alipay.v2.request;

import in.hocg.payment.alipay.v2.response.TradeRefundResponse;
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
public class TradeRefundRequest extends AliPayRequest<TradeRefundResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.refund";
    
    @Override
    protected TradeRefundResponse request() {
        return request(TradeRefundResponse.class);
    }
    
}
