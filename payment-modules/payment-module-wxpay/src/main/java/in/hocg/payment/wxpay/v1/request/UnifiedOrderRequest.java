package in.hocg.payment.wxpay.v1.request;

import in.hocg.payment.wxpay.v1.response.UnifiedOrderResponse;
import lombok.Data;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UnifiedOrderRequest extends WxPayRequest<UnifiedOrderResponse> {
    
    @Override
    protected UnifiedOrderResponse request() {
        return null;
    }
}
