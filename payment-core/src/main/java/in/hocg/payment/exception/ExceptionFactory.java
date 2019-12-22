package in.hocg.payment.exception;

import in.hocg.payment.core.ErrorContext;
import in.hocg.payment.utils.TextUtils;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2019/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ExceptionFactory {
    
    public static PaymentException wrap(String message, Object[] args, Exception e) {
        return new PersistenceException(TextUtils.format(message, args), e);
    }
    
    public static PaymentException wrap(Exception e) {
        return new PersistenceException(e);
    }
    
    public static PaymentException wrap(String message) {
        return new PersistenceException(message);
    }
    
    public static RuntimeException wrap(String message, Exception e) {
        return new PersistenceException(ErrorContext.instance().message(message).cause(e).toString(), e);
    }
}
