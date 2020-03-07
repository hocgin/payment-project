package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v2.annotation.SafeApi;
import in.hocg.payment.wxpay.v2.response.DownloadFundFlowResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3">
 * 下载资金账单
 * </a>
 *
 * @author hocgin
 */
@Data
@SafeApi
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class DownloadFundFlowRequest extends WxPayRequest<DownloadFundFlowResponse> {
    /**
     * [必选] 下载对账单的日期，格式：20140603
     */
    @ApiField(value = "bill_date")
    @XStreamAlias("bill_date")
    protected String billDate;

    /**
     * [必选] 资金账户类型
     * - Basic  基本账户
     * - Operation 运营账户
     * - Fees 手续费账户
     */
    @ApiField(value = "account_type")
    @XStreamAlias("account_type")
    protected String accountType;

    /**
     * [可选] 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
     */
    @ApiField(value = "tar_type")
    @XStreamAlias("tar_type")
    protected String tarType;

    @Override
    protected DownloadFundFlowResponse request() {
        return requestData("pay/downloadfundflow", DownloadFundFlowResponse.class);
    }
}
