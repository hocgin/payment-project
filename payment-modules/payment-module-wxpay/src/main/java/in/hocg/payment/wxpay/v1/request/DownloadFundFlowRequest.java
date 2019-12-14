package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.DownloadFundFlowResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class DownloadFundFlowRequest extends WxPayRequest<DownloadFundFlowResponse> {
    @ApiField(value = "bill_date")
    @XStreamAlias("bill_date")
    protected String billDate;
    @ApiField(value = "account_type")
    @XStreamAlias("account_type")
    protected String accountType;
    @ApiField(value = "tar_type")
    @XStreamAlias("tar_type")
    protected String tarType;
    
    @Override
    protected DownloadFundFlowResponse request() {
        return request("pay/downloadfundflow", DownloadFundFlowResponse.class);
    }
}
