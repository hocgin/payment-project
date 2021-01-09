package in.hocg.payment.complete.utils;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PaymentCompleteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PaymentCompleteException(String message) {
        super(message);
    }

    public PaymentCompleteException(Throwable throwable) {
        super(throwable);
    }

    public PaymentCompleteException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
