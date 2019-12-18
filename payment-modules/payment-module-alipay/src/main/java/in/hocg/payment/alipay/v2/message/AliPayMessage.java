package in.hocg.payment.alipay.v2.message;

import in.hocg.payment.alipay.Helpers;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.core.PaymentMessage;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.ObjectMeta;
import lombok.Getter;

import java.util.Map;

/**
 * Created by hocgin on 2019/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public abstract class AliPayMessage extends PaymentMessage<AliPayService> {
    @ApiField(value = "sign", ignore = true)
    @ObjectMeta.Alias("sign")
    private String sign;
    @ApiField(value = "sign_type", ignore = true)
    @ObjectMeta.Alias("sign_type")
    private String signType;
    
    private boolean checkSign() {
        final AliPayConfigStorage configStorage = this.getService().getConfigStorage();
        String publicKey = configStorage.getAliPayPublicKey();
        Map<String, Object> values = SignObjects.getSignValues(this);
        SignValue signValue = Helpers.newSignValue().handle(values);
        String data = signValue.getSignValue();
        return SignType.valueOf(getSignType()).verify(data, publicKey, getSign());
    }
    
    @Override
    public void afterPropertiesSet() {
        if (!checkSign()) {
            throw new RuntimeException("签名错误");
        }
    }
}
