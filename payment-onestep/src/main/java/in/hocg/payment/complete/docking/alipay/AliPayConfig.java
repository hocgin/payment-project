package in.hocg.payment.complete.docking.alipay;

import in.hocg.payment.complete.channel.PayChannel;
import in.hocg.payment.complete.config.AbsPayConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AliPayConfig extends AbsPayConfig {
    /**
     * 通知地址
     */
    private String notifyUrl;


    @Override
    public PayChannel getPayChannel() {
        AliPayChannel channel = new AliPayChannel();
        channel.setConfig(this);
        return channel;
    }
}
