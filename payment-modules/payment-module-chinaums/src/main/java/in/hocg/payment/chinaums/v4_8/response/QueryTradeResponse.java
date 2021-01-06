package in.hocg.payment.chinaums.v4_8.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/12/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryTradeResponse extends ChinaUmsPayResponse {

    /**
     * msgType : wx.orderQuery
     * payTime : 2020-12-30 16:45:17
     * connectSys : UNIONPAY
     * msgSrc : WWW.TEST.COM
     * delegatedFlag : N
     * errMsg : 查询成功
     * merName : 全渠道
     * mid : 898310148160568
     * settleDate : 2020-12-30
     * settleRefId : 00868903267N
     * tid : 88880001
     * totalAmount : 1
     * couponAmount : 0
     * targetMid : 379705922
     * responseTimestamp : 2020-12-31 09:51:06
     * errCode : SUCCESS
     * closeTime : 2020-12-30 16:46:40
     * targetStatus : SUCCESS
     * seqId : 00868903267N
     * merOrderId : 3194deo5yhzrb1s53whlg1x2xx
     * refundAmount : 0
     * status : TRADE_CLOSED
     * targetSys : WXPay
     * sign : 3A4BE8AD9202EAB4B9D2E037F7FD779DF286EBF54F8A2285A692BAD340328C31
     */
    private String msgType;
    private String payTime;
    private String connectSys;
    private String msgSrc;
    private String delegatedFlag;
    private String errMsg;
    private String merName;
    private String mid;
    private String settleDate;
    private String settleRefId;
    private String tid;
    private Integer totalAmount;
    private Integer couponAmount;
    private String targetMid;
    private String responseTimestamp;
    private String errCode;
    private String closeTime;
    private String targetStatus;
    private String seqId;
    private String merOrderId;
    private Integer refundAmount;
    private String status;
    private String targetSys;
    private String sign;
}
