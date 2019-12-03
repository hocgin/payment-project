package in.hocg.payment.wxpay.v1;

import in.hocg.payment.core.PaymentService;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class WxPayService extends PaymentService<WxConfigStorage> {
    
    public WxPayService(WxConfigStorage configStorage) {
        super(configStorage);
    }
}
