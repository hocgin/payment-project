package in.hocg.payment.wxpay.v2.response;

import in.hocg.payment.PaymentResponse;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class WxPayResponse extends PaymentResponse {
    public boolean isSign() {
        return true;
    }
}
