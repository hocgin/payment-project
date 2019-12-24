package in.hocg.payment.alipay.v2.message;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.payment.alipay.Helpers;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.PaymentMessage;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.ObjectMeta;
import lombok.Getter;

import java.net.URLDecoder;
import java.util.ArrayList;
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
        final String[] items = body.split("&");
        Map<String, Object> values = Maps.newHashMap();
        final ArrayList<String> ignoreKeys = Lists.newArrayList("sign", "sign_type");
        String[] vars;
        for (String item : items) {
            vars = item.split("=", 2);
            final String key = vars[0];
            if (ignoreKeys.contains(key)) {
                continue;
            }
            values.put(key, URLDecoder.decode(vars[1]));
        }
    
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
