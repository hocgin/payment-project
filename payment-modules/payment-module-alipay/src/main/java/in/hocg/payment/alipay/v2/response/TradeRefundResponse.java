package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeRefundResponse extends AliPayHttpResponse {
    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "buyer_logon_id")
    private String buyerLogonId;
    @JSONField(name = "fund_change")
    private String fundChange;
    @JSONField(name = "refund_fee")
    private String refundFee;
    @JSONField(name = "refund_currency")
    private String refundCurrency;
    @JSONField(name = "gmt_refund_pay")
    private String gmtRefundPay;
    @JSONField(name = "store_name")
    private String storeName;
    @JSONField(name = "buyer_user_id")
    private String buyerUserId;
    @JSONField(name = "refund_preset_paytool_list")
    private PresetPayToolInfo refundPresetPayToolList;
    @JSONField(name = "refund_settlement_id")
    private String refundSettlementId;
    @JSONField(name = "present_refund_buyer_amount")
    private String presentRefundBuyerAmount;
    @JSONField(name = "present_refund_discount_amount")
    private String presentRefundDiscountAmount;
    @JSONField(name = "present_refund_mdiscount_amount")
    private String presentRefundMdiscountAmount;
    
    @Data
    @Accessors(chain = true)
    public static class TradeFundBill {
        @JSONField(name = "fund_channel")
        private String fundChannel;
        @JSONField(name = "bank_code")
        private String bankCode;
        @JSONField(name = "amount")
        private String amount;
        @JSONField(name = "real_amount")
        private String realAmount;
        @JSONField(name = "fund_type")
        private String fundType;
    }
    
    @Data
    @Accessors(chain = true)
    public static class PresetPayToolInfo {
        @JSONField(name = "amount")
        private List<String> amount;
        @JSONField(name = "assert_type_code")
        private String assertTypeCode;
    }
}
