package in.hocg.payment.spring.boot.sample.controller.allin;

import in.hocg.payment.spring.boot.sample.controller.allin.resolve.AllInMessageResolve;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController("/all-in")
@RequiredArgsConstructor
public class AllInMessageController {
    
    private final AllInMessageResolve messageResolve;
    
    @RequestMapping("/pay/message/{channel}")
    @ResponseBody
    public ResponseEntity<String> message(@PathVariable("channel") Integer channel,
                                          @RequestBody String data) {
        return messageResolve.handle(channel, data);
    }
}
