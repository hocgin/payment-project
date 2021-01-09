package in.hocg.payment.complete.v1;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.complete.v1.channel.AliPayPaymentChannel;
import in.hocg.payment.complete.v1.channel.WxPayPaymentChannel;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PaymentPlatform {
    public static AliPayPaymentChannel AliPay(ConfigStorage configStorage) {
        return new AliPayPaymentChannel(configStorage);
    }

    public static WxPayPaymentChannel WxPay(ConfigStorage configStorage) {
        return new WxPayPaymentChannel(configStorage);
    }

}
