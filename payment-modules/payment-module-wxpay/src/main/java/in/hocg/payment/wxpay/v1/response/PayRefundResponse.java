package in.hocg.payment.wxpay.v1.response;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class PayRefundResponse
        extends WxPayXmlResponse {
    @XStreamAlias("transaction_id")
    private String transactionId;
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    @XStreamAlias("out_refund_no")
    private String outRefundNo;
    @XStreamAlias("refund_id")
    private String refundId;
    @XStreamAlias("refund_fee")
    private String refundFee;
    @XStreamAlias("settlement_refund_fee")
    private String settlementRefundFee;
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
    @XStreamAlias("cash_refund_fee")
    private String cashRefundFee;
    @XStreamAlias("coupon_refund_fee")
    private String couponRefundFee;
    @XStreamAlias("coupon_refund_count")
    private Integer couponRefundCount;
    private List<CouponRefund> couponRefunds;
    
    @Data
    @AllArgsConstructor
    public static class CouponRefund {
        private String couponType;
        private String couponFee;
        private String couponId;
    }
    
    /**
     * 解析优惠券列表
     */
    public void composeCouponRefunds() {
        if (this.couponRefundCount != null && this.couponRefundCount > 0) {
            this.couponRefunds = Lists.newArrayList();
            for (int i = 0; i < this.couponRefundCount; i++) {
                this.couponRefunds.add(new CouponRefund(this.getXmlValue("xml/coupon_refund_type_" + i),
                        this.getXmlValue("xml/coupon_refund_id_" + i),
                        this.getXmlValue("xml/coupon_refund_fee_" + i)));
            }
        }
    }
    
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        composeCouponRefunds();
    }
}
