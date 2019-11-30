package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.request.inner.ExtendParams;
import in.hocg.payment.alipay.v2.request.inner.GoodsDetail;
import in.hocg.payment.alipay.v2.request.inner.PromoParam;
import in.hocg.payment.alipay.v2.response.TradePayResponse;
import in.hocg.payment.alipay.v2.response.WrapperResponse;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.utils.LangUtils;
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
public class TradePayRequest extends AliPayRequest<WrapperResponse<TradePayResponse>> {
    
    @JSONField(name = "out_trade_no")
    @ApiField(value = "out_trade_no", required = true)
    private String outTradeNo;
    
    @JSONField(name = "scene")
    @ApiField(value = "scene", required = true)
    private String scene;
    
    @JSONField(name = "auth_code")
    @ApiField(value = "auth_code", required = true)
    private String authCode;
    
    @JSONField(name = "product_code")
    @ApiField("product_code")
    private String productCode;
    
    @JSONField(name = "subject")
    @ApiField(value = "subject", required = true)
    private String subject;
    
    @JSONField(name = "buyer_id")
    @ApiField("buyer_id")
    private String buyerId;
    
    @JSONField(name = "seller_id")
    @ApiField("seller_id")
    private String sellerId;
    
    @JSONField(name = "total_amount")
    @ApiField("total_amount")
    private String totalAmount;
    
    @JSONField(name = "trans_currency")
    @ApiField("trans_currency")
    private String transCurrency;
    
    @JSONField(name = "settle_currency")
    @ApiField("settle_currency")
    private String settleCurrency;
    
    @JSONField(name = "discountable_amount")
    @ApiField("discountable_amount")
    private String discountableAmount;
    
    @JSONField(name = "body")
    @ApiField("body")
    private String body;
    
    @JSONField(name = "goods_detail")
    @ApiField("goods_detail")
    private List<GoodsDetail> goodsDetail;
    
    @JSONField(name = "operator_id")
    @ApiField("operator_id")
    private String operatorId;
    
    @JSONField(name = "store_id")
    @ApiField("store_id")
    private String storeId;
    
    @JSONField(name = "terminal_id")
    @ApiField("terminal_id")
    private String terminalId;
    
    @JSONField(name = "extend_params")
    @ApiField("extend_params")
    private ExtendParams extendParams;
    
    @JSONField(name = "timeout_express")
    @ApiField("timeout_express")
    private String timeoutExpress;
    
    @JSONField(name = "auth_confirm_mode")
    @ApiField("auth_confirm_mode")
    private String authConfirmMode;
    
    @JSONField(name = "terminal_params")
    @ApiField("terminal_params")
    private String terminalParams;
    
    @JSONField(name = "promo_params")
    @ApiField("promo_params")
    private PromoParam promoParams;
    
    @JSONField(name = "advance_payment_type")
    @ApiField("advance_payment_type")
    private String advancePaymentType;
    
    @Override
    protected WrapperResponse<TradePayResponse> request() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        this.method = "alipay.trade.pay";
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.signType = LangUtils.getOrDefault(this.getSignType(), configStorage.getSignType().name());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), String.valueOf(System.currentTimeMillis()));
        this.sign = sign();
        return null;
    }
}
