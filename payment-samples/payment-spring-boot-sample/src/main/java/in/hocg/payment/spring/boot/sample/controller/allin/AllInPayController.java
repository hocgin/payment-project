package in.hocg.payment.spring.boot.sample.controller.allin;

import in.hocg.payment.spring.boot.sample.basic.Result;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.AllInPayResolve;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.PayType;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.pojo.AppPayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/all-in/pay")
@RequiredArgsConstructor
public class AllInPayController {
    
    private final AllInPayResolve payResolve;
    
    @RequestMapping("/app/{channel}/{feature}/{orderId}")
    @ResponseBody
    public Result pay(@PathVariable("channel") Integer channel,
                      @PathVariable("feature") Integer feature,
                      @PathVariable("orderId") String orderId) {
        final AppPayDto dto = new AppPayDto(orderId);
        return Result.success(payResolve.handle(PayType.of(channel, feature), dto));
    }
    
}
