package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeCreateResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.create">
 * alipay.trade.create(统一收单交易创建接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeCreateRequest extends AliPayRequest<TradeCreateResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.create";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        
        /**
         * [必选] 商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
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
        
        /**
         * [特殊可选] 买家的支付宝唯一用户号（2088开头的16位纯数字）
         */
        @JSONField(name = "buyer_id")
        private String buyerId;
    }
    
    
    @Override
    protected TradeCreateResponse request() {
        return request(TradeCreateResponse.class);
    }
}
