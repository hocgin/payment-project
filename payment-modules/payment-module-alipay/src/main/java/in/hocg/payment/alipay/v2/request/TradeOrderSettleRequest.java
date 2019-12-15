package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeOrderSettleResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.order.settle">
 * alipay.trade.order.settle(统一收单交易结算接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeOrderSettleRequest extends AliPayRequest<TradeOrderSettleResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.order.settle";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        
        /**
         * [必选] 结算请求流水号 开发者自行生成并保证唯一性
         */
        @JSONField(name = "out_request_no")
        private String outRequestNo;
        
        /**
         * [必选] 支付宝订单号
         */
        @JSONField(name = "trade_no")
        private String tradeNo;
        
        /**
         * [必选] 分账明细信息
         */
        @JSONField(name = "royalty_parameters")
        private List<OpenApiRoyaltyDetailInfoPojo> ropyaltyParameters;
        
        @Data
        @Accessors(chain = true)
        public static class OpenApiRoyaltyDetailInfoPojo
                extends PaymentMap {
            /**
             * [必选] 收入方账户
             */
            @JSONField(name = "trans_in")
            private String transIn;
        }
    }
    
    @Override
    protected TradeOrderSettleResponse request() {
        return request(TradeOrderSettleResponse.class);
    }
    
}
