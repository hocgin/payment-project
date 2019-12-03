package in.hocg.payment.wxpay.v1.response;

import in.hocg.payment.core.PaymentResponse;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class UnifiedOrderResponse implements PaymentResponse {
    @Override
    public boolean isSuccess() {
        return false;
    }
}
