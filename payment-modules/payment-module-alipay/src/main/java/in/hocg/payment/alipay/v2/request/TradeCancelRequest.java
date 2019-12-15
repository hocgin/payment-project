package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeCancelResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.cancel">
 * alipay.trade.cancel(统一收单交易撤销接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeCancelRequest extends AliPayRequest<TradeCancelResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.cancel";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        /**
         * [特殊可选] 原支付请求的商户订单号,和支付宝交易号不能同时为空
         */
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        
        /**
         * [特殊可选] 支付宝交易号，和商户订单号不能同时为空
         */
        @JSONField(name = "trade_no")
        private String tradeNo;
    }
    
    @Override
    protected TradeCancelResponse request() {
        return request(TradeCancelResponse.class);
    }
    
}
