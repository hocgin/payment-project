package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.response.AppPayResponse;
import in.hocg.payment.sign.SignField;
import in.hocg.payment.utils.LangUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AppPayRequest extends AliPayRequest {
    
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
    
    @Data
    public static class GoodsDetail {
        @NonNull
        @JSONField(name = "goods_id")
        private String goodsId;
    
        @NonNull
        @JSONField(name = "goods_name")
        private String goodsName;
    
        @NonNull
        @JSONField(name = "quantity")
        private String quantity;
    
        @NonNull
        @JSONField(name = "price")
        private String price;
    
        @JSONField(name = "goods_category")
        private String goodsCategory;
    
        @JSONField(name = "categories_tree")
        private String categoriesTree;
    
        @JSONField(name = "body")
        private String body;
    
        @JSONField(name = "show_url")
        private String showUrl;
    
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
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
    
    @Data
    public static class ExtendParams {
    
        @JSONField(name = "sys_service_provider_id")
        @SignField("sys_service_provider_id")
        private String sysServiceProviderId;
    
        @JSONField(name = "industry_reflux_info")
        @SignField("industry_reflux_info")
        private String industryRefluxInfo;
    
        @JSONField(name = "card_type")
        @SignField("card_type")
        private String cardType;
    
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
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
    
    @Data
    public static class PromoParam {
        
        @JSONField(name = "actual_order_time")
        @SignField("actual_order_time")
        private String actualOrderTime;
    
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @JSONField(name = "advance_payment_type")
    @SignField("advance_payment_type")
    private String advancePaymentType;
    
    @Override
    protected AppPayResponse request() {
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
