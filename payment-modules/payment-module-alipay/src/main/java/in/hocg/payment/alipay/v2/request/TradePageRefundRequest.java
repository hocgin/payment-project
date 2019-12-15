package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradePageRefundResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.page.refund">
 * alipay.trade.page.refund(统一收单退款页面接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradePageRefundRequest extends AliPayRequest<TradePageRefundResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.page.refund";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        /**
         * [特殊可选] 支付宝交易号，和商户订单号不能同时为空
         */
        @JSONField(name = "trade_no")
        private String tradeNo;
        /**
         * [特殊可选] 订单支付时传入的商户订单号,不能和 trade_no同时为空
         */
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        /**
         * [必选] 标识一次退款请求，同一笔交易多次退款需要保证唯一。
         */
        @JSONField(name = "out_request_no")
        private String outRequestNo;
        /**
         * [必选] 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数。
         */
        @JSONField(name = "refund_amount")
        private String refundAmount;
    }
    
    @Override
    protected TradePageRefundResponse request() {
        return request(TradePageRefundResponse.class);
    }
    
}
