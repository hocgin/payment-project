package in.hocg.payment;

import in.hocg.payment.bean.TextInitializingBean;
import in.hocg.payment.sign.SignScheme;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class PaymentResponse<S extends PaymentService>
        extends TextInitializingBean<S> {
    
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
    
}
