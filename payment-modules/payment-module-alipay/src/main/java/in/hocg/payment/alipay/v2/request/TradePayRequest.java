package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import in.hocg.payment.alipay.utils.LangKit;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.request.inner.ExtendParams;
import in.hocg.payment.alipay.v2.request.inner.GoodsDetail;
import in.hocg.payment.alipay.v2.request.inner.PromoParam;
import in.hocg.payment.alipay.v2.response.TradePayResponse;
import in.hocg.payment.sign.SignField;
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
public class TradePayRequest extends AliPayRequest<TradePayResponse> {
    
    @JSONField(name = "out_trade_no")
    @SignField(value = "out_trade_no", required = true)
    private String outTradeNo;
    
    @JSONField(name = "scene")
    @SignField(value = "scene", required = true)
    private String scene;
    
    @JSONField(name = "auth_code")
    @SignField(value = "auth_code", required = true)
    private String authCode;
    
    @JSONField(name = "product_code")
    @SignField("product_code")
    private String productCode;
    
    @JSONField(name = "subject")
    @SignField(value = "subject", required = true)
    private String subject;
    
    @JSONField(name = "buyer_id")
    @SignField("buyer_id")
    private String buyerId;
    
    @JSONField(name = "seller_id")
    @SignField("seller_id")
    private String sellerId;
    
    @JSONField(name = "total_amount")
    @SignField("total_amount")
    private String totalAmount;
    
    @JSONField(name = "trans_currency")
    @SignField("trans_currency")
    private String transCurrency;
    
    @JSONField(name = "settle_currency")
    @SignField("settle_currency")
    private String settleCurrency;
    
    @JSONField(name = "discountable_amount")
    @SignField("discountable_amount")
    private String discountableAmount;
    
    @JSONField(name = "body")
    @SignField("body")
    private String body;
    
    @JSONField(name = "goods_detail")
    @SignField("goods_detail")
    private List<GoodsDetail> goodsDetail;
    
    @JSONField(name = "operator_id")
    @SignField("operator_id")
    private String operatorId;
    
    @JSONField(name = "store_id")
    @SignField("store_id")
    private String storeId;
    
    @JSONField(name = "terminal_id")
    @SignField("terminal_id")
    private String terminalId;
    
    @JSONField(name = "extend_params")
    @SignField("extend_params")
    private ExtendParams extendParams;
    
    @JSONField(name = "timeout_express")
    @SignField("timeout_express")
    private String timeoutExpress;
    
    @JSONField(name = "auth_confirm_mode")
    @SignField("auth_confirm_mode")
    private String authConfirmMode;
    
    @JSONField(name = "terminal_params")
    @SignField("terminal_params")
    private String terminalParams;
    
    @JSONField(name = "promo_params")
    @SignField("promo_params")
    private PromoParam promoParams;
    
    @JSONField(name = "advance_payment_type")
    @SignField("advance_payment_type")
    private String advancePaymentType;
    
    @Override
    protected TradePayResponse request() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        this.method = "alipay.trade.pay";
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.signType = LangUtils.getOrDefault(this.getSignType(), configStorage.getSignType().name());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), String.valueOf(System.currentTimeMillis()));
        this.sign = sign();
        return LangKit.getHttpClient().get(configStorage.getUrl(), Maps.newHashMap(), TradePayResponse.class);
    }
}
