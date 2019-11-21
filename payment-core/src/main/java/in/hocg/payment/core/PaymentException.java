package in.hocg.payment.core;

import in.hocg.payment.core.utils.TextUtils;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PaymentException extends RuntimeException {
    
    
    private PaymentException(String message) {
        super(message);
    }
    
    public static PaymentException wrap(String message, Object... args) {
        return new PaymentException(TextUtils.format(message, args));
    }
}
