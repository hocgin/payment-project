package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.response.TradeAdvanceConsultResponse;
import in.hocg.payment.core.PaymentMap;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <a href="https://docs.open.alipay.com/api_1/alipay.trade.advance.consult">
 * alipay.trade.advance.consult(订单咨询服务)
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeAdvanceConsultRequest extends AliPayRequest<TradeAdvanceConsultResponse> {
    
    @ApiField(value = "method", required = true)
    protected final String method = "alipay.trade.advance.consult";
    
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BizContent
            extends PaymentMap
            implements AliPayRequest.BizContent {
        /**
         * [特殊可选] 支付宝用户id；除单笔订单风险预评估场景(即consult_scene指定ORDER_RISK_EVALUATION)外，其他场景必选。
         */
        @JSONField(name = "alipay_user_id")
        private String aliPayUserId;
        
        /**
         * [特殊可选] 订单咨询类型，用以选择咨询的服务。不传时默认为垫资咨询。
         */
        @JSONField(name = "consult_scene")
        private String consultScene;
        
        /**
         * [特殊可选] 支付宝系统中用以唯一标识用户签约记录的编号（用户签约成功后的协议号 ） ，在进行单笔订单风险评估预咨询时必传。
         */
        @JSONField(name = "agreement_no")
        private String agreementNo;
        
        /**
         * [特殊可选] 商户请求时的外部订单号，在花芝场景下非空。
         */
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
    }
    
    @Override
    protected TradeAdvanceConsultResponse request() {
        return request(TradeAdvanceConsultResponse.class);
    }
    
}
