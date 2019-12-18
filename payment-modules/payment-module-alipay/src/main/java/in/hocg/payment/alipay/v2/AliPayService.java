package in.hocg.payment.alipay.v2;

import in.hocg.payment.alipay.convert.AliPayConverts;
import in.hocg.payment.core.PaymentMessage;
import in.hocg.payment.core.PaymentService;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class AliPayService extends PaymentService<AliPayConfigStorage> {
    
    public AliPayService(AliPayConfigStorage configStorage) {
        super(configStorage);
    }
    
    @Override
    public <T extends PaymentMessage> T message(String content, Class<T> clazz) {
        return this.message(content, AliPayConverts.MESSAGE, clazz);
    }
}
