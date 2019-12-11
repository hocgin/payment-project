package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.UnifiedOrderResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class UnifiedOrderRequest extends WxPayRequest<UnifiedOrderResponse> {
    
    @ApiField(value = "device_info")
    @XStreamAlias("device_info")
    private String deviceInfo;
    
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
        return request("pay/unifiedorder", UnifiedOrderResponse.class);
    }
}
