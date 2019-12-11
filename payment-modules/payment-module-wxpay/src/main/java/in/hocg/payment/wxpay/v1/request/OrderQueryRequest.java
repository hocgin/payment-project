package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.OrderQueryResponse;
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
public class OrderQueryRequest extends WxPayRequest<OrderQueryResponse> {
    @ApiField(value = "transaction_id")
    @XStreamAlias("transaction_id")
    protected String transactionId;
    
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    protected String outTradeNo;
    
    @Override
    protected OrderQueryResponse request() {
        return request("pay/orderquery", OrderQueryResponse.class);
    }
}
