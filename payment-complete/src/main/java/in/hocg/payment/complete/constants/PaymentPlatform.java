package in.hocg.payment.complete.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/6/4.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum PaymentPlatform {
    Unknown("unknown", "未知"),
    AliPay("alipay", "支付宝"),
    WxPay("wxpay", "微信支付"),
    ChinaUMS("chinaums", "银联UMS");
    private final Serializable code;
    private final String name;
}
