package in.hocg.payment.complete.config;

import in.hocg.payment.complete.channel.PayChannel;

/**
 * Created by hocgin on 2021/12/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PayConfig {

    PayChannel getPayChannel();
}
