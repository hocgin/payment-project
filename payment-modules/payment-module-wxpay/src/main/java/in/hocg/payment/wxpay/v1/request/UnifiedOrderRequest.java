package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.UnifiedOrderResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">
 * 统一下单
 * </a>
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class UnifiedOrderRequest extends WxPayRequest<UnifiedOrderResponse> {
    
    /**
     * [可选] 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @ApiField(value = "device_info")
    @XStreamAlias("device_info")
    private String deviceInfo;
    
    /**
     * [必选] 商品简单描述，该字段请按照规范传递，具体请见参数规定
     */
    @ApiField(value = "body", required = true)
    @XStreamAlias("body")
    private String body;
    
    /**
     * [可选] 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传，详见“单品优惠参数说明”
     */
    @ApiField(value = "detail")
    @XStreamAlias("detail")
    private String detail;
    
    /**
     * [可选] 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    @ApiField(value = "attach")
    @XStreamAlias("attach")
    private String attach;
    
    /**
     * [必选] 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
     */
    @ApiField(value = "out_trade_no", required = true)
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    
    /**
     * [可选] 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     */
    @ApiField(value = "fee_type")
    @XStreamAlias("fee_type")
    private String feeType;
    
    /**
     * [必选] 订单总金额，单位为分，详见支付金额
     */
    @ApiField(value = "total_fee", required = true)
    @XStreamAlias("total_fee")
    private String totalFee;
    
    /**
     * [必选] 支持IPV4和IPV6两种格式的IP地址。用户的客户端IP
     */
    @ApiField(value = "spbill_create_ip", required = true)
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    
    /**
     * [可选] 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    @ApiField(value = "time_start")
    @XStreamAlias("time_start")
    private String timeStart;
    
    /**
     * [可选] 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     * time_expire只能第一次下单传值，不允许二次修改，二次修改系统将报错。如用户支付失败后，需再次支付，需更换原订单号重新下单。
     */
    @ApiField(value = "time_expire")
    @XStreamAlias("time_expire")
    private String timeExpire;
    
    /**
     * [可选] 订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     */
    @ApiField(value = "goods_tag")
    @XStreamAlias("goods_tag")
    private String goodsTag;
    
    /**
     * [必选] 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @ApiField(value = "notify_url", required = true)
    @XStreamAlias("notify_url")
    private String notifyUrl;
    
    /**
     * [必选] 交易类型
     * - JSAPI  - JSAPI支付
     * - NATIVE - Native支付
     * - APP    - APP支付
     * 说明详见参数规定
     */
    @ApiField(value = "trade_type", required = true)
    @XStreamAlias("trade_type")
    private String tradeType;
    
    /**
     * [特殊可选] 商品ID
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    @ApiField(value = "product_id")
    @XStreamAlias("product_id")
    private String productId;
    
    /**
     * [可选] 指定支付方式
     * - no_credit  --可限制用户不能使用信用卡支付
     */
    @ApiField(value = "limit_pay")
    @XStreamAlias("limit_pay")
    private String limitPay;
    
    /**
     * [特殊可选] 用户标识
     * trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     */
    @ApiField(value = "openid")
    @XStreamAlias("openid")
    private String openId;
    
    /**
     * [可选] 电子发票入口开放标识
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    @ApiField(value = "receipt")
    @XStreamAlias("receipt")
    private String receipt;
    
    /**
     * [可选] 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据
     * 对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开
     */
    @ApiField(value = "scene_info")
    @XStreamAlias("scene_info")
    private String sceneInfo;
    
    @Override
    protected UnifiedOrderResponse request() {
        return requestXML("pay/unifiedorder", UnifiedOrderResponse.class);
    }
}
