package in.hocg.payment.core.data;

import in.hocg.payment.PaymentResponse;
import in.hocg.payment.sign.SignScheme;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class TestResponse extends PaymentResponse {
    
    @Override
    public boolean checkSign(SignScheme scheme, String key) {
        return true;
    }
}
