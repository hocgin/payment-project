package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import in.hocg.payment.alipay.utils.LangKit;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.request.inner.ExtendParams;
import in.hocg.payment.alipay.v2.request.inner.GoodsDetail;
import in.hocg.payment.alipay.v2.request.inner.PromoParam;
import in.hocg.payment.alipay.v2.response.AppPayResponse;
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
public class AppPayRequest extends AliPayRequest<AppPayResponse> {
    
    @JSONField(name = "timeout_express")
    @SignField(value = "timeout_express")
    private String timeoutExpress;
    
    @JSONField(name = "total_amount")
    @SignField(value = "total_amount", required = true)
    private String totalAmount;
    
    @JSONField(name = "product_code")
    @SignField("product_code")
    private String productCode;
    
    @JSONField(name = "subject")
    @SignField(value = "subject", required = true)
    private String subject;
    
    @JSONField(name = "body")
    @SignField("body")
    private String body;
    
    @JSONField(name = "out_trade_no")
    @SignField(value = "out_trade_no", required = true)
    private String outTradeNo;
    
    @JSONField(name = "time_expire")
    @SignField(value = "time_expire")
    private String timeExpire;
    
    @JSONField(name = "goods_type")
    @SignField(value = "goods_type")
    private String goodsType;
    
    @JSONField(name = "promo_params")
    @SignField("promo_params")
    private PromoParam promoParams;
    
    @JSONField(name = "passback_params")
    @SignField("passback_params")
    private String passbackParams;
    
    @JSONField(name = "extend_params")
    @SignField("extend_params")
    private ExtendParams extendParams;
    
    @JSONField(name = "merchant_order_no")
    @SignField("merchant_order_no")
    private String merchantOrderNo;
    
    @JSONField(name = "enable_pay_channels")
    @SignField("enable_pay_channels")
    private String enablePayChannels;
    
    @JSONField(name = "store_id")
    @SignField("store_id")
    private String storeId;
    
    @JSONField(name = "specified_channel")
    @SignField("specified_channel")
    private String specifiedChannel;
    
    @JSONField(name = "disable_pay_channels")
    @SignField("disable_pay_channels")
    private String disablePayChannels;
    
    @JSONField(name = "goods_detail")
    @SignField("goods_detail")
    private List<GoodsDetail> goodsDetail;
    
    @JSONField(name = "ext_user_info")
    @SignField("ext_user_info")
    private ExtUserInfo extUserInfo;
    
    @Data
    public static class ExtUserInfo {
        
        @JSONField(name = "name")
        @SignField("name")
        private String name;
        
        @JSONField(name = "mobile")
        @SignField("mobile")
        private String mobile;
        
        @JSONField(name = "card_type")
        @SignField("card_type")
        private String cardType;
        
        @JSONField(name = "cert_no")
        @SignField("cert_no")
        private String certNo;
        
        @JSONField(name = "min_age")
        @SignField("min_age")
        private String minAge;
        
        @JSONField(name = "fix_buyer")
        @SignField("fix_buyer")
        private String fixBuyer;
        
        @JSONField(name = "need_check_info")
        @SignField("need_check_info")
        private String needCheckInfo;
        
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @JSONField(name = "business_params")
    @SignField("business_params")
    private String businessParams;
    
    @JSONField(name = "agreement_sign_params")
    @SignField("agreement_sign_params")
    private SignParams agreementSignParams;
    
    @Data
    static class SignParams {
        
        @JSONField(name = "personal_product_code")
        @SignField(value = "personal_product_code", required = true)
        private String personalProductCode;
        
        @JSONField(name = "sign_scene")
        @SignField(value = "sign_scene", required = true)
        private String signScene;
        
        @JSONField(name = "external_agreement_no")
        @SignField("external_agreement_no")
        private String externalAgreementNo;
        
        @JSONField(name = "external_logon_id")
        @SignField("external_logon_id")
        private String externalLogonId;
        
        @JSONField(name = "access_params")
        @SignField("access_params")
        private String accessParams;
        
        @Data
        static class AccessParams {
            @JSONField(name = "channel")
            @SignField(value = "channel", required = true)
            private String channel;
            
            @Override
            public String toString() {
                return JSON.toJSONString(this);
            }
        }
        
        @JSONField(name = "sub_merchant")
        @SignField("sub_merchant")
        private SignMerchantParams subMerchant;
        
        @Data
        static class SignMerchantParams {
            @JSONField(name = "sub_merchant_id")
            @SignField("sub_merchant_id")
            private String subMerchantId;
            
            @JSONField(name = "sub_merchant_name")
            @SignField("sub_merchant_name")
            private String subMerchantName;
            
            @JSONField(name = "sub_merchant_service_name")
            @SignField("sub_merchant_service_name")
            private String subMerchantServiceName;
            
            @JSONField(name = "sub_merchant_service_description")
            @SignField("sub_merchant_service_description")
            private String subMerchantServiceDescription;
            
            @Override
            public String toString() {
                return JSON.toJSONString(this);
            }
        }
        
        @JSONField(name = "period_rule_params")
        @SignField("period_rule_params")
        private PeriodRuleParams periodRuleParams;
        
        @Data
        static class PeriodRuleParams {
            
            @JSONField(name = "period_type")
            @SignField("period_type")
            private String periodType;
            
            @JSONField(name = "period")
            @SignField("period")
            private String period;
            
            @JSONField(name = "execute_time")
            @SignField("execute_time")
            private String executeTime;
            
            @JSONField(name = "single_amount")
            @SignField("single_amount")
            private String singleAmount;
            
            @JSONField(name = "total_amount")
            @SignField("total_amount")
            private String totalAmount;
            
            @JSONField(name = "total_payments")
            @SignField("total_payments")
            private String totalPayments;
            
            @Override
            public String toString() {
                return JSON.toJSONString(this);
            }
            
        }
        
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @Override
    protected AppPayResponse request() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        this.method = "alipay.trade.app.pay";
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.signType = LangUtils.getOrDefault(this.getSignType(), configStorage.getSignType().name());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), String.valueOf(System.currentTimeMillis()));
        this.sign = sign();
        return LangKit.getHttpClient().get(configStorage.getUrl(), Maps.newHashMap(), AppPayResponse.class);
    }
}
