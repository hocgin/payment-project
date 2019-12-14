package in.hocg.payment.wxpay.v1.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.core.PaymentMessage;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class WxPayMessage extends PaymentMessage {
    @ApiField(value = "return_code", required = true)
    @XStreamAlias("return_code")
    protected String returnCode;
    @ApiField(value = "return_msg", required = true)
    @XStreamAlias("return_msg")
    protected String returnMsg;
}
