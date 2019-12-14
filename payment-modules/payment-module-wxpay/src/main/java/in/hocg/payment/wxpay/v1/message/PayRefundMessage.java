package in.hocg.payment.wxpay.v1.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import lombok.Data;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class PayRefundMessage extends WxPayMessage {
    @ApiField(value = "appid", required = true)
    @XStreamAlias("appid")
    private String appId;
    @ApiField(value = "mch_id", required = true)
    @XStreamAlias("mch_id")
    private String mchId;
    @ApiField(value = "nonce_str", required = true)
    @XStreamAlias("nonce_str")
    private String nonceStr;
    @ApiField(value = "req_info", required = true)
    @XStreamAlias("req_info")
    private String reqInfo;
    
}
