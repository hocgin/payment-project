package in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.pojo;

import in.hocg.payment.alipay.v2.request.TradeAppPayRequest;
import lombok.Getter;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class AppPayDto {
    private final String orderId;
    
    public AppPayDto(String orderId) {
        this.orderId = orderId;
    }
    
    @Getter
    public static class Result {
        private String content;
    
        public Result(String content) {
            this.content = content;
        }
    }
    
    public static TradeAppPayRequest tradeAppPayRequest() {
        final TradeAppPayRequest request = new TradeAppPayRequest();
        request.setNotifyUrl("http://hocgin.free.idcfengye.com/alipay/message/test");
        request.setBizContent2(new TradeAppPayRequest.BizContent()
                .setSubject("iPhone Xs Max 256G")
                .setTotalAmount("0.01")
                .setOutTradeNo(String.format("%s08381158722", "code")));
        return request;
    }
}
