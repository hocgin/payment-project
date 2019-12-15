package in.hocg.payment.spring.boot.sample.controller.alipay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RequestMapping("/alipay/message")
@Controller
@RequiredArgsConstructor
public class AliPayMessageController {
    
    @GetMapping("/test")
    public void test(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
    }
}
