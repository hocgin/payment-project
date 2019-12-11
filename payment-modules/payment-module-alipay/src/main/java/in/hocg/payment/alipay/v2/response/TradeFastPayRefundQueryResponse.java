package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.request.item.RefundRoyaltyResult;
import in.hocg.payment.alipay.v2.request.item.TradeFundBill;
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
public class TradeFastPayRefundQueryResponse extends AliPayResponse {
    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "out_request_no")
    private String outRequestNo;
    @JSONField(name = "refund_reason")
    private String refundReason;
    @JSONField(name = "total_amount")
    private String totalAmount;
    @JSONField(name = "refund_amount")
    private String refundAmount;
    @JSONField(name = "refund_royaltys")
    private RefundRoyaltyResult refundRoyaltys;
    @JSONField(name = "gmt_refund_pay")
    private String gmtRefundPay;
    @JSONField(name = "refund_detail_item_list")
    private TradeFundBill refundDetailItemList;
    @JSONField(name = "send_back_fee")
    private String sendBackFee;
    @JSONField(name = "refund_settlement_id")
    private String refundSettlementId;
    @JSONField(name = "present_refund_buyer_amount")
    private String presentRefundBuyerAmount;
    @JSONField(name = "present_refund_discount_amount")
    private String presentRefundDiscountAmount;
    @JSONField(name = "present_refund_mdiscount_amount")
    private String presentRefundMdiscountAmount;
}
