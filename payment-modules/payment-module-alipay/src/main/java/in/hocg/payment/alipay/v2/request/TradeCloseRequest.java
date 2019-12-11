package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeCloseResponse;
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
public class TradeCloseRequest extends AliPayRequest<TradeCloseResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.close";
    
    @Data
    @Accessors(chain = true)
    public static class BizContent implements AliPayRequest.BizContent {
        @JSONField(name = "trade_no")
        private String tradeNo;
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        @JSONField(name = "operator_id")
        private String operatorId;
    }
    
    @Override
    protected TradeCloseResponse request() {
        return request(TradeCloseResponse.class);
    }
    
}
