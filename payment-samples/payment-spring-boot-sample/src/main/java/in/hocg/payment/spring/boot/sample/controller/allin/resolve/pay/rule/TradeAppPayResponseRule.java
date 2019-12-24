package in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.rule;

import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.request.TradeAppPayRequest;
import in.hocg.payment.alipay.v2.response.TradeAppPayResponse;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.AllInPayResolve;
import in.hocg.payment.spring.boot.sample.controller.allin.resolve.pay.pojo.AppPayDto;

import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class TradeAppPayResponseRule extends AllInPayResolve.Rule<AppPayDto, TradeAppPayRequest, String> {
    
    public TradeAppPayResponseRule(AliPayService payService) {
        super(new Convert<AppPayDto, TradeAppPayRequest>() {
            @Override
            public <R extends TradeAppPayRequest> R convert(AppPayDto basic, Class<R> clazz) {
                return (R) AppPayDto.tradeAppPayRequest();
            }
        }, new Function<TradeAppPayRequest, String>() {
            @Override
            public String apply(TradeAppPayRequest request) {
                final TradeAppPayResponse payResponse = payService.request(request);
                return payResponse.getContent();
            }
        });
    }
}
