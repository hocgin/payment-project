package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.DownloadBillResponse;
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
public class DownloadBillRequest extends WxPayRequest<DownloadBillResponse> {
    @ApiField(value = "bill_date")
    @XStreamAlias("bill_date")
    protected String billDate;
    @ApiField(value = "bill_type")
    @XStreamAlias("bill_type")
    protected String billType;
    @ApiField(value = "tar_type")
    @XStreamAlias("tar_type")
    protected String tarType;
    
    @Override
    protected DownloadBillResponse request() {
        return requestData("pay/downloadbill", DownloadBillResponse.class);
    }
}
