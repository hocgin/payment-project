package in.hocg.payment.alipay.v2.request;

import in.hocg.payment.alipay.constants.Constants;
import in.hocg.payment.alipay.utils.LangKit;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.response.TradeCreateResponse;
import in.hocg.payment.net.Converts;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.Signs;
import in.hocg.payment.sign.strategy.SignType;
import in.hocg.payment.sign.strategy.merge.DefaultMergeStrategy;
import in.hocg.payment.sign.strategy.value.DefaultValueStrategy;
import in.hocg.payment.utils.HttpUtils;
import in.hocg.payment.utils.LangUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeCreateRequest extends AliPayRequest<TradeCreateResponse> {
    
    @Override
    protected TradeCreateResponse request() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        @NonNull String privateKey = configStorage.getPrivateKey();
        String baseUrl = configStorage.getUrl();
        
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.method = "alipay.trade.create";
        this.format = LangUtils.getOrDefault(this.getFormat(), configStorage.getFormat());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), LocalDateTime.now().format(Constants.ALIPAY_API_DATE_FORMAT));
        this.version = LangUtils.getOrDefault(this.getVersion(), configStorage.getVersion());
        this.notifyUrl = LangUtils.getOrDefault(this.getNotifyUrl(), null);
        SignType signType = configStorage.getSignType();
        
        // 签名
        this.signType = LangUtils.getOrDefault(this.getSignType(), signType.name());
        Map<String, Object> values = SignObjects.getSignValues(this);
        DefaultValueStrategy valueStrategy = new DefaultValueStrategy();
        values = Signs.getString(values, valueStrategy);
        String content = Signs.merge(values, new DefaultMergeStrategy());
        this.sign = Signs.getSign(content, privateKey, signType);
        values.put("sign", this.sign);
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            Object value = entry.getValue();
            entry.setValue(URLEncoder.encode(String.valueOf(value)));
        }
        
        // 访问支付宝接口
        String url = HttpUtils.getUrl(baseUrl, values);
        Converts convert = Converts.valueOf(this.format.toUpperCase());
        return LangKit.getHttpClient().get(url, convert, TradeCreateResponse.class);
    }
}
