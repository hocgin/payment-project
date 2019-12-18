package in.hocg.payment.spring.boot.sample.controller.wxpay;

import in.hocg.payment.wxpay.v1.WxPayService;
import in.hocg.payment.wxpay.v1.message.PayRefundMessage;
import in.hocg.payment.wxpay.v1.message.UnifiedOrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hocgin on 2019/12/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RequestMapping("/wxpay/message")
@Controller
@RequiredArgsConstructor
public class WxPayMessageController {
    private final WxPayService wxPayService;
    
    @RequestMapping("/unified-order")
    @ResponseBody
    public ResponseEntity<String> unifiedOrder(@RequestBody String data) {
        UnifiedOrderMessage message = wxPayService.message(data, UnifiedOrderMessage.class);
        log.debug("通知信息: {}", message);
        return ResponseEntity.ok(UnifiedOrderMessage.Result.builder()
                .returnCode("OK")
                .returnMsg("SUCCESS")
                .build().string());
    }
    
    @RequestMapping("/pay-refund")
    @ResponseBody
    public ResponseEntity<String> payRefund(@RequestBody String data) {
        PayRefundMessage message = wxPayService.message(data, PayRefundMessage.class);
        log.debug("通知信息: {}", message);
        return ResponseEntity.ok(PayRefundMessage.Result.builder()
                .returnCode("OK")
                .returnMsg("SUCCESS")
                .build().string());
    }
}
