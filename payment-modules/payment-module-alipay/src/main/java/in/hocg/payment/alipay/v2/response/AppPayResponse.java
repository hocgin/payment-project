package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.sign.SignField;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AppPayResponse extends AliPayResponse {
    
    @JSONField(name = "trade_no")
    @SignField(value = "trade_no", required = true)
    private String tradeNo;
    
    @JSONField(name = "out_trade_no")
    @SignField(value = "out_trade_no", required = true)
    private String outTradeNo;
    
    @JSONField(name = "buyer_logon_id")
    @SignField(value = "buyer_logon_id", required = true)
    private String buyerLogonId;
    
    @JSONField(name = "settle_amount")
    @SignField(value = "settle_amount")
    private String settleAmount;
    
    @JSONField(name = "pay_currency")
    @SignField(value = "pay_currency")
    private String payCurrency;
    
    @JSONField(name = "pay_amount")
    @SignField(value = "pay_amount")
    private String payAmount;
    
    @JSONField(name = "settle_trans_rate")
    @SignField(value = "settle_trans_rate")
    private String settleTransRate;
    
    @JSONField(name = "trans_pay_rate")
    @SignField(value = "trans_pay_rate")
    private String transPayTate;
    
    @JSONField(name = "total_amount")
    @SignField(value = "total_amount", required = true)
    private String totalAmount;
    
    @JSONField(name = "trans_currency")
    @SignField(value = "trans_currency")
    private String transCurrency;
    
    @JSONField(name = "settle_currency")
    @SignField(value = "settle_currency")
    private String settleCurrency;
    
    @JSONField(name = "receipt_amount")
    @SignField(value = "receipt_amount")
    private String receiptAmount;
    
    @JSONField(name = "buyer_pay_amount")
    @SignField(value = "buyer_pay_amount")
    private String buyerPayAmount;
    
    @JSONField(name = "point_amount")
    @SignField(value = "point_amount")
    private String pointAmount;
    
    @JSONField(name = "invoice_amount")
    @SignField(value = "invoice_amount")
    private String invoiceAmount;
    
    @JSONField(name = "gmt_payment")
    @SignField(value = "gmt_payment")
    private String gmtPayment;
    
    @JSONField(name = "fund_bill_list")
    @SignField(value = "fund_bill_list", required = true)
    private List<FundBillList> fundBillList;
    
    @Getter
    static class FundBillList {
        @JSONField(name = "fund_channel")
        @SignField(value = "fund_channel", required = true)
        private String fundChannel;
        
        @JSONField(name = "bank_code")
        @SignField(value = "bank_code")
        private String bankCode;
        
        @JSONField(name = "amount")
        @SignField(value = "amount", required = true)
        private String amount;
        
        @JSONField(name = "real_amount")
        @SignField(value = "real_amount", required = true)
        private String realAmount;
        
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @JSONField(name = "card_balance")
    @SignField(value = "card_balance")
    private String cardBalance;
    
    @JSONField(name = "store_name")
    @SignField(value = "store_name")
    private String storeName;
    
    @JSONField(name = "buyer_user_id")
    @SignField(value = "buyer_user_id", required = true)
    private String buyerUserId;
    
    @JSONField(name = "discount_goods_detail")
    @SignField(value = "discount_goods_detail")
    private String discountGoodsDetail;
    
    @JSONField(name = "voucher_detail_list")
    @SignField(value = "voucher_detail_list")
    private String voucherDetailList;
    
    @Getter
    static class VoucherDetail {
        
        @JSONField(name = "id")
        @SignField(value = "id", required = true)
        private String id;
        
        @JSONField(name = "name")
        @SignField(value = "name", required = true)
        private String name;
        
        @JSONField(name = "type")
        @SignField(value = "type", required = true)
        private String type;
        
        @JSONField(name = "amount")
        @SignField(value = "amount", required = true)
        private String amount;
        
        @JSONField(name = "merchant_contribute")
        @SignField(value = "merchant_contribute")
        private String merchantContribute;
        
        @JSONField(name = "other_contribute")
        @SignField(value = "other_contribute")
        private String otherContribute;
        
        @JSONField(name = "memo")
        @SignField(value = "memo")
        private String memo;
        
        @JSONField(name = "template_id")
        @SignField(value = "template_id")
        private String templateId;
        
        @JSONField(name = "purchase_buyer_contribute")
        @SignField(value = "purchase_buyer_contribute")
        private String purchaseBuyerContribute;
        
        @JSONField(name = "purchase_merchant_contribute")
        @SignField(value = "purchase_merchant_contribute")
        private String purchaseMerchantContribute;
        
        @JSONField(name = "purchase_ant_contribute")
        @SignField(value = "purchase_ant_contribute")
        private String purchaseAntContribute;
        
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @JSONField(name = "advance_amount")
    @SignField(value = "advance_amount")
    private String advanceAmount;
    
    @JSONField(name = "auth_trade_pay_mode")
    @SignField(value = "auth_trade_pay_mode")
    private String authTradePayMode;
    
    @JSONField(name = "charge_amount")
    @SignField(value = "charge_amount")
    private String chargeAmount;
    
    @JSONField(name = "charge_flags")
    @SignField(value = "charge_flags")
    private String chargeFlags;
    
    @JSONField(name = "settlement_id")
    @SignField(value = "settlement_id")
    private String settlementId;
    
    @JSONField(name = "business_params")
    @SignField(value = "business_params")
    private String businessParams;
    
    @JSONField(name = "buyer_user_type")
    @SignField(value = "buyer_user_type")
    private String buyerUserType;
    
    @JSONField(name = "mdiscount_amount")
    @SignField(value = "mdiscount_amount")
    private String mdiscountAmount;
    
    @JSONField(name = "discount_amount")
    @SignField(value = "discount_amount")
    private String discountAmount;
    
    @JSONField(name = "buyer_user_name")
    @SignField(value = "buyer_user_name")
    private String buyerUserName;
}
