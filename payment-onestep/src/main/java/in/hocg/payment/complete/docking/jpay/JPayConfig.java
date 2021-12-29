package in.hocg.payment.complete.docking.jpay;

import in.hocg.payment.complete.channel.PayChannel;
import in.hocg.payment.complete.config.AbsPayConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JPayConfig extends AbsPayConfig {
    /**
     * 渠道编号
     */
    private final String channelId;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 应用编号
     */
    private String appid;
    /**
     * 是否开发模式
     */
    private Boolean isDev;


    @Override
    public PayChannel getPayChannel() {
        JPayChannel channel = new JPayChannel();
        channel.setConfig(this);
        return channel;
    }
}
