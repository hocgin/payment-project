package in.hocg.payment.bean;

import in.hocg.payment.PaymentService;
import in.hocg.payment.convert.StringConvert;
import lombok.Getter;

/**
 * Created by hocgin on 2019/12/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class TextInitializingBean<S extends PaymentService>
        implements InitializingBean {
    
    @Getter
    private String content;
    
    
    protected void setContent(String content) {
        this.content = content;
    }
    
    public static <T extends TextInitializingBean,
            S extends PaymentService> T from(StringConvert convert,
                                             String content,
                                             Class<T> clazz) {
        T object = (T) convert.convert(content, clazz);
        object.setContent(content);
        object.afterPropertiesSet();
        return object;
    }
}
