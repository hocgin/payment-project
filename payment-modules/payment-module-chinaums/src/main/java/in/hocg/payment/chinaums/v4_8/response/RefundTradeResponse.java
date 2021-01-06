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
public class RefundTradeResponse extends ChinaUmsPayResponse {

    /**
     * msgType : wx.refund
     * responseTimestamp : 2020-12-31 15:37:11
     * errCode : BAD_REQUEST
     * msgSrc : WWW.TEST.COM
     * errMsg : 参数不合法: refundAmount 不能为null,
     * sign : E074296E1AF6D11ED8D6094607FF5CCF602C97F4D08B17873B53DB7514A9FB64
     */
    private String msgType;
    private String responseTimestamp;
    private String errCode;
    private String msgSrc;
    private String errMsg;
    private String sign;
}
