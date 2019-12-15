package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeAppPayResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.app.pay">
 * alipay.trade.app.pay(app支付接口2.0)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeAppPayRequest extends AliPayRequest<TradeAppPayResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.app.pay";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        /**
         * [必选] 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
         */
        @JSONField(name = "total_amount")
        private String totalAmount;
        
        /**
         * [必选] 商品的标题/交易标题/订单标题/订单关键字等。
         */
        @JSONField(name = "subject")
        private String subject;
        
        /**
         * [必选] 商户网站唯一订单号
         */
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
    }
    
    @Override
    protected TradeAppPayResponse request() {
        return buildSdkParams(TradeAppPayResponse.class);
    }
    
}
