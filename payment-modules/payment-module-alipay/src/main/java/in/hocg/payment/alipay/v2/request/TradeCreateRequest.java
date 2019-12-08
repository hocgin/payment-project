package in.hocg.payment.alipay.v2.request;

import in.hocg.payment.alipay.v2.response.TradeCreateResponse;
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
public class TradeCreateRequest extends AliPayRequest<TradeCreateResponse> {
    
    @Override
    protected TradeCreateResponse request() {
        return request("alipay.trade.create",
                "alipay_trade_create_response",
                TradeCreateResponse.class);
    }
}
