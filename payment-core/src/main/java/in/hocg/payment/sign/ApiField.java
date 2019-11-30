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
public @interface ApiField {
    
    /**
     * 参数名
     *
     * @return
     */
    String value() default "";
    
    /**
     * 签名是否忽略该字段
     *
     * @return
     */
    boolean ignore() default false;
    
    /**
     * 是否必须, 目前仅为标记作用
     *
     * @return
     */
    boolean required() default false;
}
