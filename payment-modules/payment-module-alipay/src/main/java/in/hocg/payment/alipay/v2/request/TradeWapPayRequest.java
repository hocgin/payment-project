package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeWapPayResponse;
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
public class TradeWapPayRequest extends AliPayRequest<TradeWapPayResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.wap.pay";
    
    @Data
    @Accessors(chain = true)
    public static class BizContent implements AliPayRequest.BizContent {
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        @JSONField(name = "product_code")
        private String productCode;
        @JSONField(name = "quit_url")
        private String quitUrl;
        @JSONField(name = "total_amount")
        private String totalAmount;
        @JSONField(name = "subject")
        private String subject;
        // 未完, 待续
    }
    
    @Override
    protected TradeWapPayResponse request() {
        try {
            TradeWapPayResponse response = TradeWapPayResponse.class.newInstance();
            response.setContent(buildForm());
            return response;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
}
