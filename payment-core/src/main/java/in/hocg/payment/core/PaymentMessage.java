package in.hocg.payment.core;

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

