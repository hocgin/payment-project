package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.utils.LangUtils;
import in.hocg.payment.wxpay.sign.Helpers;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.utils.LangKit;
import in.hocg.payment.wxpay.v1.WxPayConfigStorage;
import in.hocg.payment.wxpay.v1.response.UnifiedOrderResponse;
import in.hocg.payment.wxpay.xml.XStreamInitializer;
import lombok.Data;

import java.util.Map;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
public class UnifiedOrderRequest extends WxPayRequest<UnifiedOrderResponse> {
    
    @ApiField(value = "app_id", required = true)
    @XStreamAlias("app_id")
    private String appId;
    
    @ApiField(value = "mch_id", required = true)
    @XStreamAlias("mch_id")
    private String mchId;
    
    @ApiField(value = "device_info")
    @XStreamAlias("device_info")
    private String deviceInfo;
    
    @ApiField(value = "nonce_str", required = true)
    @XStreamAlias("nonce_str")
    private String nonceStr;
    
    @ApiField(value = "sign", required = true, ignore = true)
    @XStreamAlias("sign")
    private String sign;
    
    @ApiField(value = "sign_type")
    @XStreamAlias("sign_type")
    private String signType;
    
    @ApiField(value = "body", required = true)
    @XStreamAlias("body")
    private String body;
    
    @ApiField(value = "detail")
    @XStreamAlias("detail")
    private String detail;
    
    @ApiField(value = "attach")
    @XStreamAlias("attach")
    private String attach;
    
    @ApiField(value = "out_trade_no", required = true)
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    
    @ApiField(value = "fee_type")
    @XStreamAlias("fee_type")
    private String feeType;
    
    @ApiField(value = "total_fee", required = true)
    @XStreamAlias("total_fee")
    private String totalFee;
    
    @ApiField(value = "spbill_create_ip", required = true)
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    
    @ApiField(value = "time_start")
    @XStreamAlias("time_start")
    private String timeStart;
    
    @ApiField(value = "time_expire")
    @XStreamAlias("time_expire")
    private String timeExpire;
    
    @ApiField(value = "goods_tag")
    @XStreamAlias("goods_tag")
    private String goodsTag;
    
    @ApiField(value = "notify_url", required = true)
    @XStreamAlias("notify_url")
    private String notifyUrl;
    
    @ApiField(value = "trade_type", required = true)
    @XStreamAlias("trade_type")
    private String tradeType;
    
    @ApiField(value = "product_id")
    @XStreamAlias("product_id")
    private String productId;
    
    @ApiField(value = "limit_pay")
    @XStreamAlias("limit_pay")
    private String limitPay;
    
    @ApiField(value = "openid")
    @XStreamAlias("openid")
    private String openId;
    
    @ApiField(value = "receipt")
    @XStreamAlias("receipt")
    private String receipt;
    
    @ApiField(value = "scene_info")
    @XStreamAlias("scene_info")
    private String sceneInfo;
    
    @Override
    protected UnifiedOrderResponse request() {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        WxPayConfigStorage configStorage = this.getPaymentService().getConfigStorage();
        String key = configStorage.getKey();
        
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.mchId = LangUtils.getOrDefault(this.getMchId(), configStorage.getMchId());
        this.notifyUrl = LangUtils.getOrDefault(this.getNotifyUrl(), null);
        
        
        Map<String, Object> values = SignObjects.getSignValues(this);
        String signString = Helpers.WxPay.getSignString(values);
        signString += String.format("&key=%s", key);
        
        this.sign = WxSignType.MD5.sign(signString, null);
        
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        String xml = xstream.toXML(this);
        String response = LangKit.getHttpClient().post(url, xml);
        UnifiedOrderResponse o = (UnifiedOrderResponse) xstream.fromXML(response);
        return null;
    }
}
