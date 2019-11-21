package in.hocg.payment.core.request;

import in.hocg.payment.core.AbsRequest;
import in.hocg.payment.core.response.AliPayResponse;
import in.hocg.payment.core.service.AliPayPaymentService;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Data
@ToString
public class AliPayRequest extends AbsRequest<AliPayPaymentService, AliPayResponse> {
    
    private Integer id;
    
    @Override
    protected AliPayResponse request() {
        log.debug("发起支付请求" + this.getClass().getName());
        return new AliPayResponse();
    }
}
