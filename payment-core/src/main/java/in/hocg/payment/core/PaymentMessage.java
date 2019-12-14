package in.hocg.payment.core;

import lombok.Getter;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class PaymentMessage implements InitializingBean {
    
    @Getter
    private String content;
    
    @Override
    public void afterPropertiesSet() {
    
    }
    
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    
    public interface Result {
    }
}

