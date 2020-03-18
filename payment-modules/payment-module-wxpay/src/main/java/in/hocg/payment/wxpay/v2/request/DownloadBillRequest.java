package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v2.response.DownloadBillResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">
 * 下载对账单
 * </a>
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class DownloadBillRequest extends WxPayRequest<DownloadBillResponse> {
    /**
     * [必选] 下载对账单的日期，格式：20140603
     */
    @ApiField(value = "bill_date")
    @XStreamAlias("bill_date")
    protected String billDate;

    /**
     * [必选] 账单类型
     * - ALL（默认值），返回当日所有订单信息（不含充值退款订单）
     * - SUCCESS，返回当日成功支付的订单（不含充值退款订单）
     * - REFUND，返回当日退款订单（不含充值退款订单）
     * - RECHARGE_REFUND，返回当日充值退款订单
     */
    @ApiField(value = "bill_type")
    @XStreamAlias("bill_type")
    protected String billType;

    /**
     * [可选] 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
     */
    @ApiField(value = "tar_type")
    @XStreamAlias("tar_type")
    protected String tarType;

    @Override
    protected DownloadBillResponse request() {
        return requestData("pay/downloadbill", DownloadBillResponse.class);
    }
}
