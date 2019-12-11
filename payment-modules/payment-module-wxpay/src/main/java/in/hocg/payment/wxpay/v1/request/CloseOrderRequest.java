package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.CloseOrderResponse;
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
public class CloseOrderRequest extends WxPayRequest<CloseOrderResponse> {
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    protected String outTradeNo;
    
    @Override
    protected CloseOrderResponse request() {
        return request("pay/closeorder", CloseOrderResponse.class);
    }
}
