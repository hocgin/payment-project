package in.hocg.payment.alipay.v2.request;

import in.hocg.payment.alipay.v2.response.TradeOrderInfoSyncResponse;
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
public class TradeOrderInfoSyncRequest extends AliPayRequest<TradeOrderInfoSyncResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.orderinfo.sync";
    
    @Override
    protected TradeOrderInfoSyncResponse request() {
        return request(TradeOrderInfoSyncResponse.class);
    }
    
}
