package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v2.annotation.SafeApi;
import in.hocg.payment.wxpay.v2.response.PayRefundResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">
 * 申请退款
 * </a>
 *
 * @author hocgin
 */
@Data
@SafeApi
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class PayRefundRequest extends WxPayRequest<PayRefundResponse> {
    /**
     * [特殊可选] 微信生成的订单号，在支付通知中有返回
     */
    @ApiField(value = "transaction_id")
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * [特殊可选] 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
     */
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * [必选] 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @ApiField(value = "out_refund_no")
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * [必选] 订单总金额，单位为分，只能为整数
     */
    @ApiField(value = "total_fee")
    @XStreamAlias("total_fee")
    private String totalFee;

    /**
     * [必选] 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
     */
    @ApiField(value = "refund_fee")
    @XStreamAlias("refund_fee")
    private String refundFee;

    /**
     * [可选] 退款货币类型，需与支付一致，或者不填。符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @ApiField(value = "refund_fee_type")
    @XStreamAlias("refund_fee_type")
    private String refundFeeType;

    /**
     * [可选] 若商户传入，会在下发给用户的退款消息中体现退款原因
     */
    @ApiField(value = "refund_desc")
    @XStreamAlias("refund_desc")
    private String refundDesc;

    /**
     * [可选] 仅针对老资金流商户使用
     * - REFUND_SOURCE_UNSETTLED_FUNDS ---未结算资金退款（默认使用未结算资金退款）
     * - REFUND_SOURCE_RECHARGE_FUNDS  ---可用余额退款
     */
    @ApiField(value = "refund_account")
    @XStreamAlias("refund_account")
    private String refundAccount;

    /**
     * [可选] 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
     */
    @ApiField(value = "notify_url")
    @XStreamAlias("notify_url")
    private String notifyUrl;

    @Override
    protected PayRefundResponse request() {
        return requestXML("secapi/pay/refund", PayRefundResponse.class);
    }
}
