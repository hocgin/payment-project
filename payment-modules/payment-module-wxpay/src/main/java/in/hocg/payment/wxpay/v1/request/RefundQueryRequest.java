package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.RefundQueryResponse;
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
public class RefundQueryRequest extends WxPayRequest<RefundQueryResponse> {
    @ApiField(value = "transaction_id")
    @XStreamAlias("transaction_id")
    protected String transactionId;
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    protected String outTradeNo;
    @ApiField(value = "out_refund_no")
    @XStreamAlias("out_refund_no")
    protected String outRefundNo;
    @ApiField(value = "refund_id")
    @XStreamAlias("refund_id")
    protected String refundId;
    
    @Override
    protected RefundQueryResponse request() {
        return request("pay/refundquery", RefundQueryResponse.class);
    }
}
