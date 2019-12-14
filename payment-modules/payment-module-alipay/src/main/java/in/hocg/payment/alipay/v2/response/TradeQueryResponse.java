package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.request.item.TradeFundBill;
import in.hocg.payment.alipay.v2.request.item.TradeSettleInfo;
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
public class TradeQueryResponse extends AliPayHttpResponse {
    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "buyer_logon_id")
    private String buyerLogonId;
    @JSONField(name = "trade_status")
    private String tradeStatus;
    @JSONField(name = "total_amount")
    private String totalAmount;
    @JSONField(name = "trans_currency")
    private String transCurrency;
    @JSONField(name = "settle_currency")
    private String settleCurrency;
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
    @JSONField(name = "buyer_pay_amount")
    private String buyerPayAmount;
    @JSONField(name = "point_amount")
    private String pointAmount;
    @JSONField(name = "invoice_amount")
    private String invoiceAmount;
    @JSONField(name = "send_pay_date")
    private String sendPayDate;
    @JSONField(name = "receipt_amount")
    private String receiptAmount;
    @JSONField(name = "store_id")
    private String storeId;
    @JSONField(name = "terminal_id")
    private String terminalId;
    @JSONField(name = "fund_bill_list")
    private TradeFundBill fundBillList;
    @JSONField(name = "store_name")
    private String storeName;
    @JSONField(name = "buyer_user_id")
    private String buyerUserId;
    @JSONField(name = "charge_amount")
    private String chargeAmount;
    @JSONField(name = "charge_flags")
    private String chargeFlags;
    @JSONField(name = "settlement_id")
    private String settlementId;
    @JSONField(name = "trade_settle_info")
    private TradeSettleInfo tradeSettleInfo;
    @JSONField(name = "auth_trade_pay_mode")
    private String authTradePayMode;
    @JSONField(name = "buyer_user_type")
    private String buyerUserType;
    @JSONField(name = "mdiscount_amount")
    private String mDiscountAmount;
    @JSONField(name = "discount_amount")
    private String discountAmount;
    @JSONField(name = "buyer_user_name")
    private String buyerUserName;
    @JSONField(name = "subject")
    private String subject;
    @JSONField(name = "body")
    private String body;
    @JSONField(name = "alipay_sub_merchant_id")
    private String alipaySubMerchantId;
    @JSONField(name = "ext_infos")
    private String extInfos;
}
