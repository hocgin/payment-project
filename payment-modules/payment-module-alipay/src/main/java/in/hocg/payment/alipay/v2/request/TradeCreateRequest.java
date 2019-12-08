package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.request.item.*;
import in.hocg.payment.alipay.v2.response.TradeCreateResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeCreateRequest extends AliPayRequest<TradeCreateResponse> {
    
    @Data
    @Accessors(chain = true)
    public static class BizContent implements AliPayRequest.BizContent {
        @JSONField(name = "out_trade_no")
        private String outTradeNo;
        @JSONField(name = "seller_id")
        private String sellerId;
        @JSONField(name = "total_amount")
        private String totalAmount;
        @JSONField(name = "discountable_amount")
        private String discountableAmount;
        @JSONField(name = "subject")
        private String subject;
        @JSONField(name = "body")
        private String body;
        @JSONField(name = "buyer_id")
        private String buyerId;
        @JSONField(name = "goods_detail")
        private List<GoodsDetail> goodsDetail;
        @JSONField(name = "product_code")
        private String productCode;
        @JSONField(name = "operator_id")
        private String operatorId;
        @JSONField(name = "store_id")
        private String storeId;
        @JSONField(name = "terminal_id")
        private String terminalId;
        @JSONField(name = "extend_params")
        private ExtendParams extendParams;
        @JSONField(name = "timeout_express")
        private String timeoutExpress;
        @JSONField(name = "settle_info")
        private SettleInfo settleInfo;
        @JSONField(name = "logistics_detail")
        private LogisticsDetail logisticsDetail;
        @JSONField(name = "business_params")
        private BusinessParams businessParams;
        @JSONField(name = "receiver_address_info")
        private ReceiverAddressInfo receiverAddressInfo;
    }
    
    
    @Override
    protected TradeCreateResponse request() {
        return request("alipay.trade.create",
                "alipay_trade_create_response",
                TradeCreateResponse.class);
    }
}
