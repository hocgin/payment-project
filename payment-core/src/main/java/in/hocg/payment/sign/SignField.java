package in.hocg.payment.sign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hocgin on 2019/11/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SignField {
    
    String value() default "";
    
    boolean ignore() default false;
    
    boolean required() default false;
}
