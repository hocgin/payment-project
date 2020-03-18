package in.hocg.payment.spring.boot.sample.controller.wxpay;

import in.hocg.payment.spring.boot.sample.utils.IpUtils;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.request.GetSignKeyRequest;
import in.hocg.payment.wxpay.v2.request.PayRefundRequest;
import in.hocg.payment.wxpay.v2.request.RefundQueryRequest;
import in.hocg.payment.wxpay.v2.request.UnifiedOrderRequest;
import in.hocg.payment.wxpay.v2.response.GetSignKeyResponse;
import in.hocg.payment.wxpay.v2.response.UnifiedOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lihuyae
 * @date 2020年03月03日 11:12:30
 */
@Slf4j
@Controller
@RequestMapping("/wxpay")
@RequiredArgsConstructor
public class WxPayController {

    private final WxPayService wxPayService;

    @ResponseBody
    @GetMapping("/refund")
    public String refundMoney() {
        PayRefundRequest req = new PayRefundRequest();
        req.setOutTradeNo("20200304123456");
        req.setOutRefundNo("TK20200306123456");
        req.setTotalFee("1");
        req.setRefundFee("1");
        req.setNotifyUrl("http://frp.remmoe.com:8080/wxpay/message/pay-refund");

        return wxPayService.request(req).getContent();
    }

    @GetMapping("pay/jsapi")
    @ResponseBody
    public String payUseJsApi() {
        return sendUnifiedOrder("JSAPI").getContent();
    }

    @GetMapping("pay/app")
    @ResponseBody
    public String payUseApp() {
        return sendUnifiedOrder("APP").getContent();
    }

    @GetMapping("pay/native")
    @ResponseBody
    public String payUseNative() {
        return sendUnifiedOrder("NATIVE").getContent();
    }

    /**
     * @param type 交易类型 JSAPI|NATIVE|APP
     * @return
     */
    private UnifiedOrderResponse sendUnifiedOrder(String type) {
        final String code = String.valueOf(System.currentTimeMillis());
        UnifiedOrderRequest unifiedOrderRequest = new UnifiedOrderRequest();
        unifiedOrderRequest.setOpenId("opQx55EOxwwO8kyQKrQePlHTOBAg");
        unifiedOrderRequest.setTradeType(type);
        unifiedOrderRequest.setBody("Lv大包");
        unifiedOrderRequest.setNotifyUrl("http://frp.remmoe.com:8080/wx/callback");
        unifiedOrderRequest.setOutTradeNo("20200304" + code);
        unifiedOrderRequest.setTotalFee("1");
        unifiedOrderRequest.setSpbillCreateIp(IpUtils.getRemoteIP());
        return wxPayService.request(unifiedOrderRequest);
    }
}
