package in.hocg.payment.spring.boot.sample.controller.allin;

import in.hocg.payment.core.PaymentMessage;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.message.AllInMessageResolve;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.message.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/all-in/message")
@RequiredArgsConstructor
public class AllInMessageController {
    
    private final AllInMessageResolve messageResolve;
    
    @RequestMapping("/{channel}/{feature}")
    @ResponseBody
    public ResponseEntity<String> message(@PathVariable("channel") Integer channel,
                                          @PathVariable("feature") Integer feature,
                                          @RequestBody String data) {
        PaymentMessage.Result result = messageResolve.handle(MessageType.of(channel, feature), data);
        return ResponseEntity.ok(result.string());
    }
}
