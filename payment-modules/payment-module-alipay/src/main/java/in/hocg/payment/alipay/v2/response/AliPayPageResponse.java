package in.hocg.payment.alipay.v2.response;

import in.hocg.payment.sign.SignScheme;

/**
 * Created by hocgin on 2019/12/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class AliPayPageResponse extends AliPayResponse {
    @Override
    public boolean checkSign(SignScheme scheme, String key) {
        return true;
    }
}
