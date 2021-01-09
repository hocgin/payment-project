package in.hocg.payment.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum PaymentPlatform {
    AliPay("alipay", "支付宝"),
    WxPay("wxpay", "微信支付"),
    ChinaUMS("chinaums", "银联UMS");
    private final Serializable code;
    private final String name;

}
