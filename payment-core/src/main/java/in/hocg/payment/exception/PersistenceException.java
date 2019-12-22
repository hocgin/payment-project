package in.hocg.payment.exception;

/**
 * Created by hocgin on 2019/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PersistenceException extends PaymentException {
    public PersistenceException(String message) {
        super(message);
    }
    
    public PersistenceException(Exception e) {
        super(e);
    }
    
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
