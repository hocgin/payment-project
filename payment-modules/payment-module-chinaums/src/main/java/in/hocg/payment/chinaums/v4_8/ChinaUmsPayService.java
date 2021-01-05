package in.hocg.payment.chinaums.v4_8;

import in.hocg.payment.PaymentMessage;
import in.hocg.payment.PaymentService;
import in.hocg.payment.chinaums.convert.ChinaUmsConverts;
import lombok.Getter;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class ChinaUmsPayService extends PaymentService<ChinaUmsConfigStorage> {

    public ChinaUmsPayService(ChinaUmsConfigStorage configStorage) {
        super(configStorage);
    }

    @Override
    public <T extends PaymentMessage> T message(String content, Class<T> clazz) {
        return this.message(content, ChinaUmsConverts.MESSAGE, clazz);
    }
}
