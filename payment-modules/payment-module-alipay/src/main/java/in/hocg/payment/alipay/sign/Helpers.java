package in.hocg.payment.alipay.sign;

import in.hocg.payment.sign.SignValue;

import java.util.Objects;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Helpers {
    
    /**
     * 支付宝签名策略
     */
    public static SignValue newSignValue() {
        SignValue signValue = new SignValue();
        return signValue.setFilter(entry -> Objects.nonNull(entry.getValue()))
                .setOrderStrategy(SignValue.KeyOrder.ASC);
    }
    
    
}
