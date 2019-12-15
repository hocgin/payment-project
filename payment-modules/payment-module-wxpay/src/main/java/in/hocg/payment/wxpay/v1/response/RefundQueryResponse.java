package in.hocg.payment.wxpay.v1.response;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

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
public class RefundQueryResponse
        extends WxPayXmlResponse {
    @XStreamAlias("total_refund_count")
    private String totalRefundCount;
    @XStreamAlias("transaction_id")
    protected String transactionId;
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    @XStreamAlias("total_fee")
    private String totalFee;
    @XStreamAlias("settlement_total_fee")
    private String settlementTotalFee;
    @XStreamAlias("fee_type")
    private String feeType;
    @XStreamAlias("cash_fee")
    private String cashFee;
    @XStreamAlias("refund_count")
    private Integer refundCount;
    
    private List<RefundInfo> refundInfos;
    
    @Data
    @RequiredArgsConstructor
    public static class RefundInfo {
        private final String outRefundNo;
        private final String refundId;
        private final String refundChannel;
        private final String refundFee;
        private final String settlementRefundFee;
        private final String couponRefundFee;
        private final Integer couponRefundCount;
        private final String refundStatus;
        private final String refundAccount;
        private final String refundRecvAccout;
        private final String refundSuccessTime;
        private List<CouponRefund> couponRefunds;
        
        @Data
        @AllArgsConstructor
        public static class CouponRefund {
            private String couponRefundId;
            private String couponType;
            private String couponRefundFee;
        }
        
    }
    
    /**
     * 解析优惠券列表
     */
    public void composeRefundInfo() {
        if (this.refundCount != null && this.refundCount > 0) {
            this.refundInfos = Lists.newArrayList();
            for (int n = 0; n < this.refundCount; n++) {
                final Integer couponRefundCount = Integer.parseInt(this.getXmlValue(String.format("xml/coupon_refund_count_%d", n)));
                
                final RefundInfo refundInfo = new RefundInfo(
                        this.getXmlValue(String.format("xml/out_refund_no_%d", n)),
                        this.getXmlValue(String.format("xml/refund_id_%d", n)),
                        this.getXmlValue(String.format("xml/refund_channel_%d", n)),
                        this.getXmlValue(String.format("xml/refund_fee_%d", n)),
                        this.getXmlValue(String.format("xml/settlement_refund_fee_%d", n)),
                        this.getXmlValue(String.format("xml/coupon_refund_fee_%d", n)),
                        couponRefundCount,
                        this.getXmlValue(String.format("xml/refund_status_%d", n)),
                        this.getXmlValue(String.format("xml/refund_account_%d", n)),
                        this.getXmlValue(String.format("xml/refund_recv_accout_%d", n)),
                        this.getXmlValue(String.format("xml/refund_success_time_%d", n))
                );
    
                if (couponRefundCount > 0) {
                    refundInfo.couponRefunds = Lists.newArrayList();
                    for (int m = 0; m < couponRefundCount; m++) {
                        refundInfo.couponRefunds.add(new RefundInfo.CouponRefund(
                                this.getXmlValue(String.format("xml/coupon_refund_id_%d_%d", n, m)),
                                this.getXmlValue(String.format("xml/coupon_type_%d_%d", n, m)),
                                this.getXmlValue(String.format("xml/coupon_refund_fee_%d_%d", n, m))
                        ));
                    }
                }
            }
        }
    }
    
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        this.composeRefundInfo();
    }
}
