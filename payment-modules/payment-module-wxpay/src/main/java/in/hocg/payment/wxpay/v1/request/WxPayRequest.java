package in.hocg.payment.wxpay.v1.request;

import in.hocg.payment.core.PaymentRequest;
import in.hocg.payment.core.PaymentResponse;
import in.hocg.payment.wxpay.v1.WxPayService;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class WxPayRequest<R extends PaymentResponse>
        extends PaymentRequest<WxPayService, R> {
    
}
