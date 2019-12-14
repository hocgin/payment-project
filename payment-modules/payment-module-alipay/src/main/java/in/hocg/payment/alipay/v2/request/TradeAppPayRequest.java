package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeAppPayResponse;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeAppPayRequest extends AliPayRequest<TradeAppPayResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.app.pay";
    
    @Data
    @Accessors(chain = true)
    public static class BizContent implements AliPayRequest.BizContent {
        @JSONField(name = "total_amount")
        private String totalAmount;
        @JSONField(name = "subject")
        private String subject;
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
    }
    
    @Override
    protected TradeAppPayResponse request() {
        return buildSdkParams(TradeAppPayResponse.class);
    }
    
}
