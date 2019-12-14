package in.hocg.payment.wxpay.v1.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.SignScheme;
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
public class DownloadFundFlowResponse extends WxPayResponse {
    
    @Override
    public boolean checkSign(SignScheme scheme, String key) {
        return true;
    }
}
