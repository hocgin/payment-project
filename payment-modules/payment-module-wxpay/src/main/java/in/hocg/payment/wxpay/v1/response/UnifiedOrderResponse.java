package in.hocg.payment.wxpay.v1.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.core.PaymentResponse;
import lombok.Data;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
public class UnifiedOrderResponse implements PaymentResponse {
    
    @XStreamAlias("return_code")
    private String returnCode;
    
    @XStreamAlias("return_msg")
    private String returnMsg;
    
    @Override
    public boolean isSuccess() {
        return false;
    }
}
