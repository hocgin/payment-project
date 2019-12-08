package in.hocg.payment.wxpay.v1.response;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class UnifiedOrderResponse extends WxPayResponse {
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
    private List<Coupon> coupons;
    
    @Data
    @AllArgsConstructor
    public static class Coupon {
        private String couponType;
        private String couponId;
        private String couponFee;
    }
    
    /**
     * 解析优惠券列表
     */
    public void composeCoupons() {
        if (this.couponCount != null && this.couponCount > 0) {
            this.coupons = Lists.newArrayList();
            for (int i = 0; i < this.couponCount; i++) {
                this.coupons.add(new Coupon(this.getXmlValue("xml/coupon_type_" + i),
                        this.getXmlValue("xml/coupon_id_" + i),
                        this.getXmlValue("xml/coupon_fee_" + i)));
            }
        }
    }
    
    @Override
    public void after() {
        composeCoupons();
    }
    
    @Override
    public boolean isSuccess() {
        return false;
    }
}
