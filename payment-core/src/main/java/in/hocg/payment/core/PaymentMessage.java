package in.hocg.payment.core;

import com.sun.deploy.ref.Helpers;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class PaymentMessage<S extends PaymentService>
        extends PaymentInitializingBean<S> {
    
    public interface Result {
    }
}

