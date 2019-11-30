package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.sign.ApiField;
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
    @ApiField(value = "trade_no", required = true)
    private String tradeNo;
    
    @JSONField(name = "out_trade_no")
    @ApiField(value = "out_trade_no", required = true)
    private String outTradeNo;
    
    @JSONField(name = "buyer_logon_id")
    @ApiField(value = "buyer_logon_id", required = true)
    private String buyerLogonId;
    
    @JSONField(name = "settle_amount")
    @ApiField(value = "settle_amount")
    private String settleAmount;
    
    @JSONField(name = "pay_currency")
    @ApiField(value = "pay_currency")
    private String payCurrency;
    
    @JSONField(name = "pay_amount")
    @ApiField(value = "pay_amount")
    private String payAmount;
    
    @JSONField(name = "settle_trans_rate")
    @ApiField(value = "settle_trans_rate")
    private String settleTransRate;
    
    @JSONField(name = "trans_pay_rate")
    @ApiField(value = "trans_pay_rate")
    private String transPayTate;
    
    @JSONField(name = "total_amount")
    @ApiField(value = "total_amount", required = true)
    private String totalAmount;
    
    @JSONField(name = "trans_currency")
    @ApiField(value = "trans_currency")
    private String transCurrency;
    
    @JSONField(name = "settle_currency")
    @ApiField(value = "settle_currency")
    private String settleCurrency;
    
    @JSONField(name = "receipt_amount")
    @ApiField(value = "receipt_amount")
    private String receiptAmount;
    
    @JSONField(name = "buyer_pay_amount")
    @ApiField(value = "buyer_pay_amount")
    private String buyerPayAmount;
    
    @JSONField(name = "point_amount")
    @ApiField(value = "point_amount")
    private String pointAmount;
    
    @JSONField(name = "invoice_amount")
    @ApiField(value = "invoice_amount")
    private String invoiceAmount;
    
    @JSONField(name = "gmt_payment")
    @ApiField(value = "gmt_payment")
    private String gmtPayment;
    
    @JSONField(name = "fund_bill_list")
    @ApiField(value = "fund_bill_list", required = true)
    private List<FundBillList> fundBillList;
    
    @Getter
    static class FundBillList {
        @JSONField(name = "fund_channel")
        @ApiField(value = "fund_channel", required = true)
        private String fundChannel;
        
        @JSONField(name = "bank_code")
        @ApiField(value = "bank_code")
        private String bankCode;
        
        @JSONField(name = "amount")
        @ApiField(value = "amount", required = true)
        private String amount;
        
        @JSONField(name = "real_amount")
        @ApiField(value = "real_amount", required = true)
        private String realAmount;
        
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @JSONField(name = "card_balance")
    @ApiField(value = "card_balance")
    private String cardBalance;
    
    @JSONField(name = "store_name")
    @ApiField(value = "store_name")
    private String storeName;
    
    @JSONField(name = "buyer_user_id")
    @ApiField(value = "buyer_user_id", required = true)
    private String buyerUserId;
    
    @JSONField(name = "discount_goods_detail")
    @ApiField(value = "discount_goods_detail")
    private String discountGoodsDetail;
    
    @JSONField(name = "voucher_detail_list")
    @ApiField(value = "voucher_detail_list")
    private String voucherDetailList;
    
    @Getter
    static class VoucherDetail {
        
        @JSONField(name = "id")
        @ApiField(value = "id", required = true)
        private String id;
        
        @JSONField(name = "name")
        @ApiField(value = "name", required = true)
        private String name;
        
        @JSONField(name = "type")
        @ApiField(value = "type", required = true)
        private String type;
        
        @JSONField(name = "amount")
        @ApiField(value = "amount", required = true)
        private String amount;
        
        @JSONField(name = "merchant_contribute")
        @ApiField(value = "merchant_contribute")
        private String merchantContribute;
        
        @JSONField(name = "other_contribute")
        @ApiField(value = "other_contribute")
        private String otherContribute;
        
        @JSONField(name = "memo")
        @ApiField(value = "memo")
        private String memo;
        
        @JSONField(name = "template_id")
        @ApiField(value = "template_id")
        private String templateId;
        
        @JSONField(name = "purchase_buyer_contribute")
        @ApiField(value = "purchase_buyer_contribute")
        private String purchaseBuyerContribute;
        
        @JSONField(name = "purchase_merchant_contribute")
        @ApiField(value = "purchase_merchant_contribute")
        private String purchaseMerchantContribute;
        
        @JSONField(name = "purchase_ant_contribute")
        @ApiField(value = "purchase_ant_contribute")
        private String purchaseAntContribute;
        
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @JSONField(name = "advance_amount")
    @ApiField(value = "advance_amount")
    private String advanceAmount;
    
    @JSONField(name = "auth_trade_pay_mode")
    @ApiField(value = "auth_trade_pay_mode")
    private String authTradePayMode;
    
    @JSONField(name = "charge_amount")
    @ApiField(value = "charge_amount")
    private String chargeAmount;
    
    @JSONField(name = "charge_flags")
    @ApiField(value = "charge_flags")
    private String chargeFlags;
    
    @JSONField(name = "settlement_id")
    @ApiField(value = "settlement_id")
    private String settlementId;
    
    @JSONField(name = "business_params")
    @ApiField(value = "business_params")
    private String businessParams;
    
    @JSONField(name = "buyer_user_type")
    @ApiField(value = "buyer_user_type")
    private String buyerUserType;
    
    @JSONField(name = "mdiscount_amount")
    @ApiField(value = "mdiscount_amount")
    private String mdiscountAmount;
    
    @JSONField(name = "discount_amount")
    @ApiField(value = "discount_amount")
    private String discountAmount;
    
    @JSONField(name = "buyer_user_name")
    @ApiField(value = "buyer_user_name")
    private String buyerUserName;
}
