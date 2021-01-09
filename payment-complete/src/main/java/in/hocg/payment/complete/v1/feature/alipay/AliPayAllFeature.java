package in.hocg.payment.complete.v1.feature.alipay;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.PaymentService;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.complete.v1.feature.BasePaymentFeature;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AliPayAllFeature extends BasePaymentFeature
    implements AliPayPaymentFeature, AliPayRefundFeature {

    public AliPayAllFeature(ConfigStorage configStorage) {
        super(configStorage);
    }

    @Override
    public <T extends PaymentService<?>> T getPaymentService() {
        return (T) PaymentServices.createPaymentService(AliPayService.class, getConfigStorage());
    }
}
