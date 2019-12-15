package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradePreCreateResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.precreate">
 * alipay.trade.precreate(统一收单线下交易预创建)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradePreCreateRequest extends AliPayRequest<TradePreCreateResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.precreate";
    
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
         * [必选] 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
         */
        @JSONField(name = "total_amount")
        private String totalAmount;
        /**
         * [必选] 订单标题
         */
        @JSONField(name = "subject")
        private String subject;
    }
    
    @Override
    protected TradePreCreateResponse request() {
        return request(TradePreCreateResponse.class);
    }
    
}
