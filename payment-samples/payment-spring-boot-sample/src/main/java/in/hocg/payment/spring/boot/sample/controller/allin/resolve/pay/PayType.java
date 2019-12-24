package in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum PayType {
    WX_xx;
    
    public static PayType of(Integer channel, Integer feature) {
        return PayType.WX_xx;
    }
}
