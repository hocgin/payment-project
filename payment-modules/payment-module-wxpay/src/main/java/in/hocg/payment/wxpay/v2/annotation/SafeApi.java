package in.hocg.payment.wxpay.v2.annotation;

import java.lang.annotation.*;

/**
 * @author lihuaye
 * @date 2020-03-05 21:56:13
 * <p>
 * 是否使用api证书
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SafeApi {
    /**
     * 是否使用api证书
     */
    boolean value() default true;
}
