package in.hocg.payment.wxpay.v1;

import in.hocg.payment.PaymentMessage;
import in.hocg.payment.PaymentService;
import in.hocg.payment.wxpay.convert.WxPayConverts;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class WxPayService extends PaymentService<WxPayConfigStorage> {
    
    public WxPayService(WxPayConfigStorage configStorage) {
        super(configStorage);
    }
    
    @Override
    public <T extends PaymentMessage> T message(String content, Class<T> clazz) {
        return message(content, WxPayConverts.MESSAGE, clazz);
    }
}
