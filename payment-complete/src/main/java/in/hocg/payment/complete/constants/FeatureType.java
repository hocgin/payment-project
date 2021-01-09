package in.hocg.payment.complete.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum FeatureType {
    Refund("refund", "退款"),
    Payment("payment", "支付");
    private final Serializable code;
    private final String name;
}
