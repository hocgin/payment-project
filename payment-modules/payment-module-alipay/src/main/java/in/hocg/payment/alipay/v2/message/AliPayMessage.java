package in.hocg.payment.alipay.v2.message;

import com.google.common.collect.Lists;
import in.hocg.payment.PaymentMessage;
import in.hocg.payment.alipay.Helpers;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.MapUtils;
import in.hocg.payment.utils.ObjectMeta;
import lombok.Getter;

import java.util.List;
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
        String body = getContent();
        final List<String> ignoreKeys = Lists.newArrayList("sign", "sign_type");
        Map<String, Object> values = MapUtils.getValues(body, ignoreKeys);

        final AliPayConfigStorage configStorage = this.getService().getConfigStorage();
        String publicKey = configStorage.getAliPayPublicKey();
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

    public interface Result extends PaymentMessage.Result {

    }
}
