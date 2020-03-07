package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v2.response.RefundQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">
 * 查询退款
 * </a>
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class RefundQueryRequest extends WxPayRequest<RefundQueryResponse> {
    /**
     * [特殊可选] 微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
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

    /**
     * [特殊可选] 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @ApiField(value = "out_refund_no")
    @XStreamAlias("out_refund_no")
    protected String outRefundNo;

    /**
     * [特殊可选] 微信生成的退款单号，在申请退款接口有返回
     */
    @ApiField(value = "refund_id")
    @XStreamAlias("refund_id")
    protected String refundId;

    /**
     * [可选] 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     */
    @ApiField(value = "offset")
    @XStreamAlias("offset")
    private String offset;

    @Override
    protected RefundQueryResponse request() {
        return requestXML("pay/refundquery", RefundQueryResponse.class);
    }
}
