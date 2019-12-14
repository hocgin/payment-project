package in.hocg.payment.core;

import in.hocg.payment.sign.SignScheme;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class PaymentResponse implements InitializingBean {
    
    @Getter
    private String content;
    
    /**
     * 检查签名
     *
     * @param scheme
     * @param key
     * @return
     */
    public boolean checkSign(SignScheme scheme, String key) {
        return false;
    }
    
    @Override
    public void afterPropertiesSet() {
    }
    
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    
}
