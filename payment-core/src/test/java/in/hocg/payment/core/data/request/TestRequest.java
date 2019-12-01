package in.hocg.payment.core.data.request;

import in.hocg.payment.core.PaymentRequest;
import in.hocg.payment.core.data.TestPaymentService;
import in.hocg.payment.core.data.response.TestResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class TestRequest extends PaymentRequest<TestPaymentService, TestResponse> {
    
    @Override
    protected TestResponse request() {
        log.debug("执行请求");
        return new TestResponse();
    }
}
