package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.PayRefundResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class PayRefundRequest extends WxPayRequest<PayRefundResponse> {
    @ApiField(value = "transaction_id")
    @XStreamAlias("transaction_id")
    private String transactionId;
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    @ApiField(value = "out_refund_no")
    @XStreamAlias("out_refund_no")
    private String outRefundNo;
    @ApiField(value = "total_fee")
    @XStreamAlias("total_fee")
    private String totalFee;
    @ApiField(value = "refund_fee")
    @XStreamAlias("refund_fee")
    private String refundFee;
    @ApiField(value = "refund_fee_type")
    @XStreamAlias("refund_fee_type")
    private String refundFeeType;
    @ApiField(value = "refund_desc")
    @XStreamAlias("refund_desc")
    private String refundDesc;
    @ApiField(value = "refund_account")
    @XStreamAlias("refund_account")
    private String refundAccount;
    @ApiField(value = "notify_url")
    @XStreamAlias("notify_url")
    private String notifyUrl;
    
    @Override
    protected PayRefundResponse request() {
        return request("secapi/pay/refund", PayRefundResponse.class);
    }
}
