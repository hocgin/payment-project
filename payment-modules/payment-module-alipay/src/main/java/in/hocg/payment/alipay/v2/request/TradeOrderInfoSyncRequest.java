package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeOrderInfoSyncResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.orderinfo.sync">
 * alipay.trade.orderinfo.sync(支付宝订单信息同步接口)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeOrderInfoSyncRequest extends AliPayRequest<TradeOrderInfoSyncResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.orderinfo.sync";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        
        /**
         * [必选] 支付宝交易号
         */
        @JSONField(name = "trade_no")
        private String tradeNo;
        
        
        /**
         * [必选] 标识一笔交易多次请求，同一笔交易多次信息同步时需要保证唯一
         */
        @JSONField(name = "out_request_no")
        private String outRequestNo;
        
        /**
         * [必选] 交易信息同步对应的业务类型，具体值与支付宝约定；
         * - 信用授权场景下传 CREDIT_AUTH
         * - 信用代扣场景下传 CREDIT_DEDUCT
         */
        @JSONField(name = "biz_type")
        private String bizType;
    }
    
    @Override
    protected TradeOrderInfoSyncResponse request() {
        return request(TradeOrderInfoSyncResponse.class);
    }
    
}
