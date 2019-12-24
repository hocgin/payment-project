package in.hocg.payment.spring.boot.sample.controller.allin.resolve.message;

import lombok.NonNull;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum MessageType {
    /**
     * 微信支付 - 消息通知
     */
    WxPay_PayRefund(1, 0),
    WxPay_UnifiedOrder(1, 1),
    
    /**
     * 支付宝支付 - 消息通知
     */
    AliPay_TradeStatusSync(2, 0),
    ;
    
    private final Integer channel;
    private final Integer feature;
    
    MessageType(@NonNull Integer channel,
                @NonNull Integer feature) {
        this.channel = channel;
        this.feature = feature;
    }
    
    public static MessageType of(@NonNull Integer channel,
                                 @NonNull Integer feature) {
        for (MessageType type : MessageType.values()) {
            if (type.channel.equals(channel) && type.feature.equals(feature)) {
                return type;
            }
        }
        throw new UnsupportedOperationException();
    }
}
