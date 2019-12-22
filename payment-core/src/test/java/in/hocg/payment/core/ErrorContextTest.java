package in.hocg.payment.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class ErrorContextTest {
    
    @Test
    void instance() {
        final ErrorContext instance = ErrorContext.instance();
        instance.requestBody("{请求参数}");
        instance.activity("场景");
        instance.url("/url/url");
        instance.requestBody("{响应参数}");
        instance.cause(new Exception("异常"));
        instance.message("描述信息");
        
        log.info("信息: {}", instance);
    }
}