package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeFastPayRefundQueryResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query">
 * alipay.trade.fastpay.refund.query(统一收单交易退款查询)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeFastPayRefundQueryRequest extends AliPayRequest<TradeFastPayRefundQueryResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.fastpay.refund.query";
    
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
         * [特殊可选] 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
         */
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        
        /**
         * [必选] 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
         */
        @JSONField(name = "out_request_no")
        private String outRequestNo;
    }
    
    @Override
    protected TradeFastPayRefundQueryResponse request() {
        return request(TradeFastPayRefundQueryResponse.class);
    }
    
}
