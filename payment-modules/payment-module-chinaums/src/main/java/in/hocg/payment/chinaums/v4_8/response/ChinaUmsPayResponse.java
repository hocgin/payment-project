package in.hocg.payment.chinaums.v4_8.response;

import in.hocg.payment.PaymentResponse;
import in.hocg.payment.chinaums.v4_8.ChinaUmsPayService;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ChinaUmsPayResponse extends PaymentResponse<ChinaUmsPayService> {

}
