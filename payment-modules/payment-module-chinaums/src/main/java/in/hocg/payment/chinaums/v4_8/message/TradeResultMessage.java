package in.hocg.payment.chinaums.v4_8.message;

import lombok.Getter;

/**
 * Created by hocgin on 2021/1/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class TradeResultMessage extends ChinaUmsPayMessage {
    private String mid;
    private String tid;
    private String instMid;
    private String attachedData;
    private String bankCardNo;
    private String bankInfo;
    private String billFunds;
    private String billFundsDesc;
    private String buyerId;
    private String buyerUsername;
    private Integer couponAmount;
    private Integer buyerPayAmount;
    private Integer totalAmount;
    private String invoiceAmount;
    private String merOrderId;
    private String payTime;
    private Integer receiptAmount;
    private String refId;
    private Integer refundAmount;
    private String refundDesc;
    private String seqId;
    private String settleDate;
    private String status;
    private String subBuyerId;
    private String targetOrderId;
    private String targetSys;
    private String secureStatus;
    private Integer completeAmount;
    private String notifyId;
    private Integer couponMerchantContribute;
    private Integer couponOtherContribute;
    private String refundTargetOrderId;
    private String refundPayTime;
    private String refundSettleDate;
    private String orderDesc;
    private String createTime;
    private String mchntUuid;
    private String connectSys;
    private String subInst;
    private Integer yxlmAmount;
    private String refundExtOrderId;
    private String goodsTradeNo;
}
