package in.hocg.payment.chinaums.v4_8.message;

import in.hocg.payment.PaymentMessage;
import in.hocg.payment.chinaums.v4_8.ChinaUmsPayService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class ChinaUmsPayMessage extends PaymentMessage<ChinaUmsPayService> {

}
