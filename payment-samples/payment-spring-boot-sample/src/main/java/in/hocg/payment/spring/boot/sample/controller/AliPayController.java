package in.hocg.payment.spring.boot.sample.controller;

import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.request.AliPayRequest;
import in.hocg.payment.alipay.v2.request.TradeCreateRequest;
import in.hocg.payment.alipay.v2.request.TradePreCreateRequest;
import in.hocg.payment.alipay.v2.response.TradePreCreateResponse;
import in.hocg.payment.spring.boot.sample.utils.QrCodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
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
    public void payUseApp() {
    }
    
    /**
     * 支付 - PC
     */
    @GetMapping("pay/pc")
    public void payUsePc() {
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
        final String orderNo = String.valueOf(System.currentTimeMillis());
        final AliPayRequest<TradePreCreateResponse> tradePreCreateRequest = new TradePreCreateRequest()
                .setBizContent2(new TradeCreateRequest.BizContent()
                        .setTotalAmount("88.88")
                        .setSubject("iPhone Xs Max 256G")
                        .setBuyerId("2088102175953034")
                        .setOutTradeNo(String.format("%s08381158722", orderNo)));
        final TradePreCreateResponse tradePreCreateResponse = aliPayService.request(tradePreCreateRequest);
        log.debug("{}", tradePreCreateResponse);
        
        // 将结果转为二维码图片
        final String code = tradePreCreateResponse.getQrCode();
        final BufferedImage image = QrCodeUtils.getQrCode(code, 400, 400);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        return ResponseEntity.ok().body(bos.toByteArray());
    }
}
