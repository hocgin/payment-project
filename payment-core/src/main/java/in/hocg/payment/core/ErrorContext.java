package in.hocg.payment.core;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ErrorContext {
    
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();
    
    private ErrorContext stored;
    
    private ErrorContext() {
    }
    
    public static ErrorContext instance() {
        ErrorContext context = LOCAL.get();
        if (context == null) {
            context = new ErrorContext();
            LOCAL.set(context);
        }
        return context;
    }
    
    public ErrorContext store() {
        stored = this;
        LOCAL.set(new ErrorContext());
        return LOCAL.get();
    }
    
    public ErrorContext recall() {
        if (stored != null) {
            LOCAL.set(stored);
            stored = null;
        }
        return LOCAL.get();
    }
    
    public ErrorContext reset() {
        
        LOCAL.remove();
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append(super.toString());
        return description.toString();
    }
}
