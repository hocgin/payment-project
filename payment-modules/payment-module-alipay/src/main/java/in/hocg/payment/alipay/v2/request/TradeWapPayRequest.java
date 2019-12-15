package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeWapPayResponse;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.wap.pay">
 * alipay.trade.wap.pay(手机网站支付接口2.0)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeWapPayRequest extends AliPayRequest<TradeWapPayResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.wap.pay";
    
    @Data
    @Accessors(chain = true)
    public static class BizContent implements AliPayRequest.BizContent {
        /**
         * [必选] 商户网站唯一订单号
         */
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        
        /**
         * [必选] 销售产品码，商家和支付宝签约的产品码
         * - QUICK_WAP_WAY
         */
        @JSONField(name = "product_code")
        private String productCode;
        
        /**
         * [必选] 用户付款中途退出返回商户网站的地址
         */
        @JSONField(name = "quit_url")
        private String quitUrl;
        
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
    }
    
    @Override
    protected TradeWapPayResponse request() {
        return buildForm(TradeWapPayResponse.class);
    }
    
}
