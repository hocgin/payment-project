package in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay;

import lombok.NonNull;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum PayType {
    AliPay_TradeAppPay(2, 0);
    
    private final Integer channel;
    private final Integer feature;
    
    PayType(@NonNull Integer channel,
            @NonNull Integer feature) {
        this.channel = channel;
        this.feature = feature;
    }
    
    public static PayType of(@NonNull Integer channel,
                             @NonNull Integer feature) {
        for (PayType type : PayType.values()) {
            if (type.channel.equals(channel) && type.feature.equals(feature)) {
                return type;
            }
        }
        throw new UnsupportedOperationException();
    }
}
