package in.hocg.payment.complete.v1.feature.wxpay;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.PaymentService;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.complete.v1.feature.BasePaymentFeature;
import in.hocg.payment.wxpay.v2.WxPayService;

/**
 * Created by hocgin on 2021/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WxPayAllFeature extends BasePaymentFeature {

    public WxPayAllFeature(ConfigStorage configStorage) {
        super(configStorage);
    }

    @Override
    public <T extends PaymentService<?>> T getPaymentService() {
        return (T) PaymentServices.createPaymentService(WxPayService.class, getConfigStorage());
    }
}
