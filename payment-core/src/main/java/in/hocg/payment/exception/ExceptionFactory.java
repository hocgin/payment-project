package in.hocg.payment.exception;

import in.hocg.payment.core.ErrorContext;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2019/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ExceptionFactory {
    
    public static RuntimeException wrap(String message, Exception e) {
        return new PersistenceException(ErrorContext.instance().message(message).cause(e).toString(), e);
    }
}
