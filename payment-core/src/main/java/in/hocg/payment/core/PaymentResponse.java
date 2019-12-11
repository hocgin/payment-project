package in.hocg.payment.core;

import in.hocg.payment.net.Convert;
import in.hocg.payment.sign.SignScheme;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class PaymentResponse {
    
    @Getter
    @Setter
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
    
    protected void afterPropertiesSet() {
    }
    
    public static <T extends PaymentResponse> T from(Convert convert, String content, Class<T> clazz) {
        T object = (T) convert.convert(content, clazz);
        object.setContent(content);
        object.afterPropertiesSet();
        return object;
    }
    
}
