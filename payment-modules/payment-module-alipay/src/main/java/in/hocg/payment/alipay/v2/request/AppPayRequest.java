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
public class AppPayRequest extends AliPayRequest<AppPayResponse> {
    
    @JSONField(name = "timeout_express")
    @ApiField(value = "timeout_express")
    private String timeoutExpress;
    
    @JSONField(name = "total_amount")
    @ApiField(value = "total_amount", required = true)
    private String totalAmount;
    
    @JSONField(name = "product_code")
    @ApiField("product_code")
    private String productCode;
    
    @JSONField(name = "subject")
    @ApiField(value = "subject", required = true)
    private String subject;
    
    @JSONField(name = "body")
    @ApiField("body")
    private String body;
    
    @JSONField(name = "out_trade_no")
    @ApiField(value = "out_trade_no", required = true)
    private String outTradeNo;
    
    @JSONField(name = "time_expire")
    @ApiField(value = "time_expire")
    private String timeExpire;
    
    @JSONField(name = "goods_type")
    @ApiField(value = "goods_type")
    private String goodsType;
    
    @JSONField(name = "promo_params")
    @ApiField("promo_params")
    private PromoParam promoParams;
    
    @JSONField(name = "passback_params")
    @ApiField("passback_params")
    private String passbackParams;
    
    @JSONField(name = "extend_params")
    @ApiField("extend_params")
    private ExtendParams extendParams;
    
    @JSONField(name = "merchant_order_no")
    @ApiField("merchant_order_no")
    private String merchantOrderNo;
    
    @JSONField(name = "enable_pay_channels")
    @ApiField("enable_pay_channels")
    private String enablePayChannels;
    
    @JSONField(name = "store_id")
    @ApiField("store_id")
    private String storeId;
    
    @JSONField(name = "specified_channel")
    @ApiField("specified_channel")
    private String specifiedChannel;
    
    @JSONField(name = "disable_pay_channels")
    @ApiField("disable_pay_channels")
    private String disablePayChannels;
    
    @JSONField(name = "goods_detail")
    @ApiField("goods_detail")
    private List<GoodsDetail> goodsDetail;
    
    @JSONField(name = "ext_user_info")
    @ApiField("ext_user_info")
    private ExtUserInfo extUserInfo;
    
    @Data
    public static class ExtUserInfo {
        
        @JSONField(name = "name")
        @ApiField("name")
        private String name;
        
        @JSONField(name = "mobile")
        @ApiField("mobile")
        private String mobile;
        
        @JSONField(name = "card_type")
        @ApiField("card_type")
        private String cardType;
        
        @JSONField(name = "cert_no")
        @ApiField("cert_no")
        private String certNo;
        
        @JSONField(name = "min_age")
        @ApiField("min_age")
        private String minAge;
        
        @JSONField(name = "fix_buyer")
        @ApiField("fix_buyer")
        private String fixBuyer;
        
        @JSONField(name = "need_check_info")
        @ApiField("need_check_info")
        private String needCheckInfo;
        
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
    
    @JSONField(name = "business_params")
    @ApiField("business_params")
    private String businessParams;
    
    @JSONField(name = "agreement_sign_params")
    @ApiField("agreement_sign_params")
    private SignParams agreementSignParams;
    
    @Data
    static class SignParams {
        
        @JSONField(name = "personal_product_code")
        @ApiField(value = "personal_product_code", required = true)
        private String personalProductCode;
        
        @JSONField(name = "sign_scene")
        @ApiField(value = "sign_scene", required = true)
        private String signScene;
        
        @JSONField(name = "external_agreement_no")
        @ApiField("external_agreement_no")
        private String externalAgreementNo;
        
        @JSONField(name = "external_logon_id")
        @ApiField("external_logon_id")
        private String externalLogonId;
        
        @JSONField(name = "access_params")
        @ApiField("access_params")
        private String accessParams;
        
        @Data
        static class AccessParams {
            @JSONField(name = "channel")
            @ApiField(value = "channel", required = true)
            private String channel;
            
            @Override
            public String toString() {
                return JSON.toJSONString(this);
            }
        }
        
        @JSONField(name = "sub_merchant")
        @ApiField("sub_merchant")
        private SignMerchantParams subMerchant;
        
        @Data
        static class SignMerchantParams {
            @JSONField(name = "sub_merchant_id")
            @ApiField("sub_merchant_id")
            private String subMerchantId;
            
            @JSONField(name = "sub_merchant_name")
            @ApiField("sub_merchant_name")
            private String subMerchantName;
            
            @JSONField(name = "sub_merchant_service_name")
            @ApiField("sub_merchant_service_name")
            private String subMerchantServiceName;
            
            @JSONField(name = "sub_merchant_service_description")
            @ApiField("sub_merchant_service_description")
            private String subMerchantServiceDescription;
            
            @Override
            public String toString() {
                return JSON.toJSONString(this);
            }
        }
        
        @JSONField(name = "period_rule_params")
        @ApiField("period_rule_params")
        private PeriodRuleParams periodRuleParams;
        
        @Data
        static class PeriodRuleParams {
            
            @JSONField(name = "period_type")
            @ApiField("period_type")
            private String periodType;
            
            @JSONField(name = "period")
            @ApiField("period")
            private String period;
            
            @JSONField(name = "execute_time")
            @ApiField("execute_time")
            private String executeTime;
            
            @JSONField(name = "single_amount")
            @ApiField("single_amount")
            private String singleAmount;
            
            @JSONField(name = "total_amount")
            @ApiField("total_amount")
            private String totalAmount;
            
            @JSONField(name = "total_payments")
            @ApiField("total_payments")
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
