package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v2.response.OrderQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">
 * 查询订单
 * </a>
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class OrderQueryRequest extends WxPayRequest<OrderQueryResponse> {

    /**
     * [特殊可选] 微信的订单号，建议优先使用
     */
    @ApiField(value = "transaction_id")
    @XStreamAlias("transaction_id")
    protected String transactionId;

    /**
     * [特殊可选] 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    protected String outTradeNo;

    @Override
    protected OrderQueryResponse request() {
        return requestXML("pay/orderquery", OrderQueryResponse.class);
    }
}
