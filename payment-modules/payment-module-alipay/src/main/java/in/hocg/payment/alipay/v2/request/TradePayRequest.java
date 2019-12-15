package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradePayResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.pay">
 * alipay.trade.pay(统一收单交易支付接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradePayRequest extends AliPayRequest<TradePayResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.pay";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        /**
         * [必选] 商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
         */
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        /**
         * [必选] 支付场景
         * - 条码支付，取值：bar_code
         * - 声波支付，取值：wave_code
         */
        @JSONField(name = "scene")
        private String scene;
        /**
         * [必选] 支付授权码，25~30开头的长度为16~24位的数字，实际字符串长度以开发者获取的付款码长度为准
         */
        @JSONField(name = "auth_code")
        private String authCode;
        /**
         * [必选] 订单标题
         */
        @JSONField(name = "subject")
        private String subject;
    }
    
    @Override
    protected TradePayResponse request() {
        return request(TradePayResponse.class);
    }
    
}
