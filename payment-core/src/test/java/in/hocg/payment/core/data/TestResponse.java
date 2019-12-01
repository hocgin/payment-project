package in.hocg.payment.core.data;

import in.hocg.payment.core.PaymentResponse;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class TestResponse implements PaymentResponse {
    
    @Override
    public boolean isSuccess() {
        return true;
    }
}
