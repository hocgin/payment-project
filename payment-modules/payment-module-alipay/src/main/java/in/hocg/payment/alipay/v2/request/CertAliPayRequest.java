package in.hocg.payment.alipay.v2.request;

import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.response.AppPayResponse;
import in.hocg.payment.core.AbsPaymentRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class CertAliPayRequest extends AbsPaymentRequest<AliPayService, AppPayResponse> {
    
    @Override
    protected AppPayResponse request() {
        return null;
    }
    
    @Override
    public void help() {
        log.debug("签名方式支持两种: 普通公钥、公钥证书");
    }
}
