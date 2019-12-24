package in.hocg.payment.alipay.v2.response;

import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.PaymentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AliPayResponse extends PaymentResponse<AliPayService> {
}
