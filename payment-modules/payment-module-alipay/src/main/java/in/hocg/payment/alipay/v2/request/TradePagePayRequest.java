package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradePagePayResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.page.pay">
 * alipay.trade.page.pay(统一收单下单并支付页面接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradePagePayRequest extends AliPayRequest<TradePagePayResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.page.pay";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        @JSONField(name = "product_code")
        private String productCode;
        @JSONField(name = "total_amount")
        private String totalAmount;
        @JSONField(name = "subject")
        private String subject;
    }
    
    @Override
    protected TradePagePayResponse request() {
        return buildForm(TradePagePayResponse.class);
    }
    
}
