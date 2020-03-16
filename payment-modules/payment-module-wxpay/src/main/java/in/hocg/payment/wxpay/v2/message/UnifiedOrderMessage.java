package in.hocg.payment.wxpay.v2.message;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.exception.PaymentException;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignScheme;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import lombok.*;

import java.util.List;
import java.util.Map;


/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 * <p>
 * See <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7&index=8">支付结果通知</a>
 *
 * @author hocgin
 * @see in.hocg.payment.wxpay.v2.response.OrderQueryResponse
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class UnifiedOrderMessage extends WxPayMessage {
    @XStreamAlias("appid")
    private String appId;
    @XStreamAlias("mch_id")
    private String mchId;
    @XStreamAlias("nonce_str")
    private String nonceStr;
    @XStreamAlias("sign")
    private String sign;
    @XStreamAlias("sign_type")
    private String signType;
    @XStreamAlias("result_code")
    private String resultCode;
    @XStreamAlias("err_code")
    private String errCode;
    @XStreamAlias("err_code_des")
    private String errCodeDes;
    @XStreamAlias("device_info")
    private String deviceInfo;
    @XStreamAlias("openid")
    private String openid;
    @XStreamAlias("is_subscribe")
    private String isSubscribe;
    @XStreamAlias("trade_type")
    private String tradeType;
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
    private List<UnifiedOrderMessage.Coupon> coupons;

    @Data
    @AllArgsConstructor
    public static class Coupon {
        private String couponType;
        private String couponId;
        private String couponFee;
    }

    private void composeCoupons() {
        if (this.couponCount != null && this.couponCount > 0) {
            this.coupons = Lists.newArrayList();
            for (int i = 0; i < this.couponCount; i++) {
                this.coupons.add(new UnifiedOrderMessage.Coupon(
                        this.getXmlValue("xml/coupon_type_" + i),
                        this.getXmlValue("xml/coupon_id_" + i),
                        this.getXmlValue("xml/coupon_fee_" + i)));
            }
        }
    }


    private boolean checkSign() {
        final WxPayConfigStorage configStorage = getService().getConfigStorage();
        @NonNull final String key = configStorage.getKey();
        Map<String, Object> data = this.toMap();
        data.remove("sign");
        SignValue signHelper = Helpers.newSignValue().handle(data);
        String signValue = signHelper.getSignValue();
        signValue += String.format("&key=%s", key);

        SignScheme signType = WxSignType.of(getSignType()).useLogger();
        return signType.verify(signValue, key, this.getSign());
    }

    @Override
    public void afterPropertiesSet() {
        composeCoupons();
        if (!checkSign()) {
            throw new PaymentException("支付消息签名不一致");
        }
    }

    @Data
    @Builder
    @XStreamAlias("xml")
    public static class Result implements WxPayMessage.Result {
        @ApiField(value = "return_code", required = true)
        @XStreamAlias("return_code")
        protected String returnCode;
        @ApiField(value = "return_msg", required = true)
        @XStreamAlias("return_msg")
        protected String returnMsg;
    }
}
