package in.hocg.payment.spring.boot.sample.controller.alipay;

import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.request.*;
import in.hocg.payment.alipay.v2.response.TradeAppPayResponse;
import in.hocg.payment.alipay.v2.response.TradePagePayResponse;
import in.hocg.payment.alipay.v2.response.TradePreCreateResponse;
import in.hocg.payment.alipay.v2.response.TradeWapPayResponse;
import in.hocg.payment.spring.boot.sample.utils.QrCodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by hocgin on 2019/12/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RequestMapping("/alipay")
@Controller
@RequiredArgsConstructor
public class AliPayController {
    private final AliPayService aliPayService;
    
    /**
     * 支付 - APP
     */
    @GetMapping("pay/app")
    @ResponseBody
    public String payUseApp() {
        final String code = String.valueOf(System.currentTimeMillis());
        final TradeAppPayRequest tradeAppPayRequest = new TradeAppPayRequest();
        tradeAppPayRequest.setNotifyUrl("http://hocgin.free.idcfengye.com/alipay/message/test");
        tradeAppPayRequest.setBizContent2(new TradeAppPayRequest.BizContent()
                .setSubject("iPhone Xs Max 256G")
                .setTotalAmount("0.01")
                .setOutTradeNo(String.format("%s08381158722", code)));
        final TradeAppPayResponse tradeAppPayResponse = aliPayService.request(tradeAppPayRequest);
        log.debug("{}", tradeAppPayResponse);
        return tradeAppPayResponse.getContent();
    }
    
    /**
     * 支付 - Wap/Native
     */
    @GetMapping("pay/wap")
    @ResponseBody
    public void payUseWap(HttpServletResponse httpResponse) throws IOException {
        final String code = String.valueOf(System.currentTimeMillis());
        final TradeWapPayRequest tradeWapPayRequest = new TradeWapPayRequest();
        tradeWapPayRequest.setBizContent2(new TradeWapPayRequest.BizContent()
                .setSubject("iPhone Xs Max 256G")
                .setTotalAmount("0.01")
                .setProductCode("QUICK_WAP_WAY")
                .setQuitUrl("http://hocg.in")
                .setOutTradeNo(String.format("%s08381158722", code)));
        final TradeWapPayResponse tradeWapPayResponse = aliPayService.request(tradeWapPayRequest);
        
        // 直接将完整的表单html输出到页面
        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.getWriter().write(tradeWapPayResponse.getContent());
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
    
    /**
     * 支付 - PC
     * 文档链接: <a href="https://docs.open.alipay.com/270/105899/">电脑网站支付</a>
     */
    @GetMapping("pay/pc")
    public void payUsePc(HttpServletResponse httpResponse) throws IOException {
        final String code = String.valueOf(System.currentTimeMillis());
        final TradePagePayRequest tradePagePayRequest = new TradePagePayRequest();
        tradePagePayRequest.setBizContent2(new TradePagePayRequest.BizContent()
                .setTotalAmount("0.01")
                .setSubject("iPhone Xs Max 256G")
                .setProductCode("FAST_INSTANT_TRADE_PAY")
                .setOutTradeNo(String.format("%s08381158722", code)));
        final TradePagePayResponse tradePagePayResponse = aliPayService.request(tradePagePayRequest);
        log.debug("{}", tradePagePayResponse);
        
        // 直接将完整的表单html输出到页面
        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.getWriter().write(tradePagePayResponse.getContent());
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
    
    /**
     * 支付 - 二维码
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "pay/qr-code", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
    @ResponseBody
    public ResponseEntity<byte[]> payUseQrCode() throws IOException {
        final String code = String.valueOf(System.currentTimeMillis());
        final AliPayRequest<TradePreCreateResponse> tradePreCreateRequest = new TradePreCreateRequest()
                .setBizContent2(new TradeCreateRequest.BizContent()
                        .setTotalAmount("0.01")
                        .setSubject("iPhone Xs Max 256G")
                        .setBuyerId(code)
                        .setOutTradeNo(String.format("%s08381158722", code)));
        final TradePreCreateResponse tradePreCreateResponse = aliPayService.request(tradePreCreateRequest);
        log.debug("{}", tradePreCreateResponse);
        
        // 将结果转为二维码图片
        final String content = tradePreCreateResponse.getQrCode();
        final BufferedImage image = QrCodeUtils.getQrCode(content, 400, 400);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        return ResponseEntity.ok().body(bos.toByteArray());
    }
}
