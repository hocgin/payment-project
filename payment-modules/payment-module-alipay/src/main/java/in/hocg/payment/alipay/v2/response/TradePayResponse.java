package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.request.item.TradeFundBill;
import in.hocg.payment.alipay.v2.request.item.VoucherDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TradePayResponse extends AliPayResponse {
    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "buyer_logon_id")
    private String buyerLogonId;
    @JSONField(name = "settle_amount")
    private String settleAmount;
    @JSONField(name = "pay_currency")
    private String payCurrency;
    @JSONField(name = "pay_amount")
    private String payAmount;
    @JSONField(name = "settle_trans_rate")
    private String settleTransRate;
    @JSONField(name = "trans_pay_rate")
    private String transPayRate;
    @JSONField(name = "total_amount")
    private String totalAmount;
    @JSONField(name = "trans_currency")
    private String transCurrency;
    @JSONField(name = "settle_currency")
    private String settleCurrency;
    @JSONField(name = "receipt_amount")
    private String receiptAmount;
    @JSONField(name = "buyer_pay_amount")
    private String buyerPayAmount;
    @JSONField(name = "point_amount")
    private String pointAmount;
    @JSONField(name = "invoice_amount")
    private String invoiceAmount;
    @JSONField(name = "gmt_payment")
    private String gmtPayment;
    @JSONField(name = "fund_bill_list")
    private TradeFundBill fundBillList;
    @JSONField(name = "card_balance")
    private String cardBalance;
    @JSONField(name = "storeName")
    private String storeName;
    @JSONField(name = "buyer_user_id")
    private String buyerUserId;
    @JSONField(name = "discount_goods_detail")
    private String discountGoodsDetail;
    @JSONField(name = "voucher_detail_list")
    private VoucherDetail voucherDetailList;
    @JSONField(name = "advance_amount")
    private String advanceAmount;
    @JSONField(name = "auth_trade_pay_mode")
    private String authTradePayMode;
    @JSONField(name = "charge_amount")
    private String chargeAmount;
    @JSONField(name = "charge_flags")
    private String chargeFlags;
    @JSONField(name = "settlement_id")
    private String settlementId;
    @JSONField(name = "business_params")
    private String businessParams;
    @JSONField(name = "buyer_user_type")
    private String buyerUserType;
    @JSONField(name = "mdiscount_amount")
    private String mDiscountAmount;
    @JSONField(name = "discount_amount")
    private String discountAmount;
    @JSONField(name = "buyer_user_name")
    private String buyerUserName;
}
