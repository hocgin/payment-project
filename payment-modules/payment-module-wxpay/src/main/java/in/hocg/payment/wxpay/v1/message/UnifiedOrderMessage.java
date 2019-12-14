package in.hocg.payment.wxpay.v1.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.wxpay.v1.response.OrderQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 *
 * See <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7&index=8">支付结果通知</a>
 * @see in.hocg.payment.wxpay.v1.response.OrderQueryResponse
 * @author hocgin
 */
@Data
public class UnifiedOrderMessage extends WxPayMessage {
    @XStreamAlias("device_info")
    private String deviceInfo;
    @XStreamAlias("openid")
    private String openid;
    @XStreamAlias("is_subscribe")
    private String isSubscribe;
    @XStreamAlias("trade_type")
    private String tradeType;
    @XStreamAlias("trade_state")
    private String tradeState;
    @XStreamAlias("bank_type")
    private String bankType;
    @XStreamAlias("total_fee")
    private String totalFee;
    @XStreamAlias("settlement_total_fee")
    private String settlementTotalFee;
    @XStreamAlias("fee_type")
    private String feeType;
    @XStreamAlias("cash_fee")
    private String cashFee;
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;
    @XStreamAlias("coupon_fee")
    private String couponFee;
    @XStreamAlias("coupon_count")
    private Integer couponCount;
    @XStreamAlias("transaction_id")
    private String transactionId;
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    @XStreamAlias("attach")
    private String attach;
    @XStreamAlias("time_end")
    private String timeEnd;
    @XStreamAlias("trade_state_desc")
    private String tradeStateDesc;
    private List<OrderQueryResponse.Coupon> coupons;
    
    @Data
    @AllArgsConstructor
    public static class Coupon {
        private String couponType;
        private String couponId;
        private String couponFee;
    }
    
    
}
