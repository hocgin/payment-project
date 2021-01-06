package in.hocg.payment.chinaums.v4_8.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/12/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CloseTradeResponse extends ChinaUmsPayResponse {

    /**
     * msgType : trade.query
     * payTime : 2020-12-30 16:30:26
     * connectSys : ALIPAY
     * msgSrc : WWW.TEST.COM
     * delegatedFlag : N
     * errMsg : 交易不存在
     * merName : 全渠道
     * mid : 898310148160568
     * settleDate : 2020-12-30
     * settleRefId : 00868903129N
     * tid : 88880001
     * totalAmount : 1
     * targetMid : 2015061000120322
     * responseTimestamp : 2020-12-30 16:32:47
     * errCode : SUCCESS
     * targetStatus : 40004|ACQ.TRADE_NOT_EXIST
     * seqId : 00868903129N
     * merOrderId : 3194deo5yhzrb1s53whlg1x2
     * refundAmount : 0
     * status : NEW_ORDER
     * targetSys : Alipay 2.0
     * sign : 22EFA45C2B5D58E0BC13510F48D367DAF6F46157A8FE02F4823DD24BADB5A728
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
    private String targetMid;
    private String responseTimestamp;
    private String errCode;
    private String targetStatus;
    private String seqId;
    private String merOrderId;
    private Integer refundAmount;
    private String status;
    private String targetSys;
    private String sign;
}
