package in.hocg.payment.complete.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class TradeInfo {
    /**
     * [必须] 商户单号
     */
    private String outTradeSn;
    /**
     * 订单标题
     */
    private String title;
    /**
     * [必须] 通知地址
     */
    private String notifyUrl;
    /**
     * [必须] 支付总金额
     */
    private BigDecimal totalAmount;

    // ======== 可选 ========
    /**
     * 中断回跳地址
     * -
     */
    private String quitUrl;
    /**
     * 购买者IP
     * - 微信支付
     */
    private String buyerIp;
    /**
     * Openid
     * - 微信支付
     */
    private String openid;
}
