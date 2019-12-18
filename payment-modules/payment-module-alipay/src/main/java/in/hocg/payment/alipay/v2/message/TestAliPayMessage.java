package in.hocg.payment.alipay.v2.message;

import in.hocg.payment.sign.ApiField;
import in.hocg.payment.utils.ObjectMeta;
import lombok.Getter;

/**
 * Created by hocgin on 2019/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class TestAliPayMessage extends AliPayMessage {
    @ApiField("gmt_create")
    @ObjectMeta.Alias("gmt_create")
    private String gmtCreate;
    @ApiField("charset")
    @ObjectMeta.Alias("charset")
    private String charset;
    @ApiField("seller_email")
    @ObjectMeta.Alias("seller_email")
    private String sellerEmail;
    @ApiField("subject")
    @ObjectMeta.Alias("subject")
    private String subject;
    @ApiField("buyer_id")
    @ObjectMeta.Alias("buyer_id")
    private String buyerId;
    @ApiField("invoice_amount")
    @ObjectMeta.Alias("invoice_amount")
    private String invoiceAmount;
    @ApiField("notify_id")
    @ObjectMeta.Alias("notify_id")
    private String notifyId;
    @ApiField("fund_bill_list")
    @ObjectMeta.Alias("fund_bill_list")
    private String fundBillList;
    @ApiField("notify_type")
    @ObjectMeta.Alias("notify_type")
    private String notifyType;
    @ApiField("trade_status")
    @ObjectMeta.Alias("trade_status")
    private String tradeStatus;
    @ApiField("receipt_amount")
    @ObjectMeta.Alias("receipt_amount")
    private String receiptAmount;
    @ApiField("app_id")
    @ObjectMeta.Alias("app_id")
    private String appId;
    @ApiField("buyer_pay_amount")
    @ObjectMeta.Alias("buyer_pay_amount")
    private String buyerPayAmount;
    @ApiField("seller_id")
    @ObjectMeta.Alias("seller_id")
    private String sellerId;
    @ApiField("gmt_payment")
    @ObjectMeta.Alias("gmt_payment")
    private String gmtPayment;
    @ApiField("notify_time")
    @ObjectMeta.Alias("notify_time")
    private String notifyTime;
    @ApiField("version")
    @ObjectMeta.Alias("version")
    private String version;
    @ApiField("out_trade_no")
    @ObjectMeta.Alias("out_trade_no")
    private String outTradeNo;
    @ApiField("total_amount")
    @ObjectMeta.Alias("total_amount")
    private String totalAmount;
    @ApiField("trade_no")
    @ObjectMeta.Alias("trade_no")
    private String tradeNo;
    @ApiField("auth_app_id")
    @ObjectMeta.Alias("auth_app_id")
    private String authAppId;
    @ApiField("buyer_logon_id")
    @ObjectMeta.Alias("buyer_logon_id")
    private String buyerLogonId;
    @ApiField("point_amount")
    @ObjectMeta.Alias("point_amount")
    private String pointAmount;
}
