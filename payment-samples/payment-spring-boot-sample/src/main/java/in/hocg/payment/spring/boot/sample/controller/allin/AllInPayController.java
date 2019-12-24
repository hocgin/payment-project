package in.hocg.payment.spring.boot.sample.controller.allin;

import in.hocg.payment.spring.boot.sample.basic.Result;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.AllInPayResolve;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.PayType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    
    @RequestMapping("/{channel}/{feature}")
    @ResponseBody
    public Result pay(@PathVariable("channel") Integer channel,
                      @PathVariable("feature") Integer feature,
                      @RequestBody String data) {
        return Result.success(payResolve.handle(PayType.of(channel, feature), data));
    }
    
}
