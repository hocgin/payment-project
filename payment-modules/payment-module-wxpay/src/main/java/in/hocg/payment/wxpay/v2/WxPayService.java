package in.hocg.payment.wxpay.v2;

import in.hocg.payment.PaymentMessage;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.PaymentResponse;
import in.hocg.payment.PaymentService;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.convert.WxPayConverts;
import in.hocg.payment.wxpay.v2.annotation.SafeApi;
import lombok.Getter;
import lombok.NonNull;

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
        Helpers.initCertHttpClient(configStorage);
    }

    @Override
    public <T extends PaymentMessage> T message(String content, Class<T> clazz) {
        return message(content, WxPayConverts.MESSAGE, clazz);
    }
}
