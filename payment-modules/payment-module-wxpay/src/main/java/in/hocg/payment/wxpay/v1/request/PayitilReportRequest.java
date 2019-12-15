package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.PayitilReportResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8&index=9">
 * 交易保障
 * </a>
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class PayitilReportRequest extends WxPayRequest<PayitilReportResponse> {
    
    /**
     * [必选] 对应的接口的完整URL，类似：
     * <p>
     * https://api.mch.weixin.qq.com/pay/unifiedorder
     * <p>
     * 对于刷卡支付，为更好的和商户共同分析一次业务行为的整体耗时情况，对于两种接入模式，请都在门店侧对一次刷卡支付进行一次单独的整体上报，上报URL指定为：
     * <p>
     * https://api.mch.weixin.qq.com/pay/micropay/total
     */
    @ApiField(value = "interface_url")
    @XStreamAlias("interface_url")
    protected String interfaceUrl;
    
    /**
     * [必选] 接口耗时情况，单位为毫秒
     */
    @ApiField(value = "execute_time")
    @XStreamAlias("execute_time")
    protected String executeTime;
    
    /**
     * [可选] 商户系统内部的订单号,商户可以在上报时提供相关商户订单号方便微信支付更好的提高服务质量。
     */
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    protected String outTradeNo;
    
    /**
     * [必选] 发起接口调用时的机器IP
     */
    @ApiField(value = "user_ip")
    @XStreamAlias("user_ip")
    protected String userIp;
    
    /**
     * [可选] 系统时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010
     */
    @ApiField(value = "time")
    @XStreamAlias("time")
    protected String time;
    
    @Override
    protected PayitilReportResponse request() {
        return requestXML("payitil/report", PayitilReportResponse.class);
    }
}
