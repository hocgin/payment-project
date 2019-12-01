package in.hocg.payment.alipay.v2.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class TradeCreateResponse extends AliPayResponse {
    
    @Override
    public boolean isSuccess() {
        return false;
    }
}
