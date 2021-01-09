package in.hocg.payment.complete.v1.channel;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.complete.v1.feature.alipay.AliPayAllFeature;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AliPayPaymentChannel extends BasePaymentChannel {

    public AliPayPaymentChannel(ConfigStorage configStorage) {
        super(configStorage);
    }

    public AliPayAllFeature AliPay() {
        return new AliPayAllFeature(getConfigStorage());
    }
}

