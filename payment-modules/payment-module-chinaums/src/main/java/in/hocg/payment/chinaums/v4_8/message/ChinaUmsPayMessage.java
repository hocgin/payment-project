package in.hocg.payment.chinaums.v4_8.message;

import com.google.common.collect.Lists;
import in.hocg.payment.PaymentMessage;
import in.hocg.payment.chinaums.Helpers;
import in.hocg.payment.chinaums.v4_8.ChinaUmsConfigStorage;
import in.hocg.payment.chinaums.v4_8.ChinaUmsPayService;
import in.hocg.payment.sign.SignScheme;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.utils.MapUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class ChinaUmsPayMessage extends PaymentMessage<ChinaUmsPayService> {
    private String sign;
    private String signType;

    private boolean checkSign() {
        String body = getContent();
        final List<String> ignoreKeys = Lists.newArrayList("sign");
        Map<String, Object> values = MapUtils.getValues(body, ignoreKeys);

        final ChinaUmsConfigStorage configStorage = this.getService().getConfigStorage();
        String publicKey = configStorage.getTxmKey();
        SignValue signValue = Helpers.newSignValue().handle(values);
        String data = signValue.getSignValue();
        SignScheme signType = Helpers.getSignType(this.signType);
        return signType.verify(data, publicKey, this.sign);
    }

    @Override
    public void afterPropertiesSet() {
        if (!checkSign()) {
            throw new RuntimeException("签名错误");
        }
    }
}
