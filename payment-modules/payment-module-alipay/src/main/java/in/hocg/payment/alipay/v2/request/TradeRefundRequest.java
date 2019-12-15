package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeRefundResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.refund">
 * alipay.trade.refund(统一收单交易退款接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeRefundRequest extends AliPayRequest<TradeRefundResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.refund";
    
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
        
        /**
         * [必选] 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
         */
        @JSONField(name = "refund_amount")
        private String refundAmount;
    }
    
    @Override
    protected TradeRefundResponse request() {
        return request(TradeRefundResponse.class);
    }
    
}
