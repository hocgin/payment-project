package in.hocg.payment.complete.v1.channel;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.complete.v1.feature.wxpay.WxPayAllFeature;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WxPayPaymentChannel extends BasePaymentChannel {

    public WxPayPaymentChannel(ConfigStorage configStorage) {
        super(configStorage);
    }

    public WxPayAllFeature WxPay() {
        return new WxPayAllFeature(getConfigStorage());
    }
}

