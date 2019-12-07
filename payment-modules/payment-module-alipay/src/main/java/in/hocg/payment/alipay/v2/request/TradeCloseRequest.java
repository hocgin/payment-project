package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import in.hocg.payment.PaymentException;
import in.hocg.payment.alipay.constants.Constants;
import in.hocg.payment.alipay.utils.LangKit;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.response.TradeCreateResponse;
import in.hocg.payment.net.Converts;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
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
public class TradeCloseRequest extends AliPayRequest<TradeCreateResponse> {
    
    @Override
    protected TradeCreateResponse request() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        @NonNull String privateKey = configStorage.getPrivateKey();
        @NonNull String aliPayPublicKey = configStorage.getAliPayPublicKey();
        String baseUrl = configStorage.getUrl();
        
        this.appId = LangUtils.getOrDefault(this.getAppId(), configStorage.getAppId());
        this.method = "alipay.trade.create";
        this.format = LangUtils.getOrDefault(this.getFormat(), configStorage.getFormat());
        this.charset = LangUtils.getOrDefault(this.getCharset(), configStorage.getCharset());
        this.timestamp = LangUtils.getOrDefault(this.getTimestamp(), LocalDateTime.now().format(Constants.ALIPAY_API_DATE_FORMAT));
        this.version = LangUtils.getOrDefault(this.getVersion(), configStorage.getVersion());
        this.notifyUrl = LangUtils.getOrDefault(this.getNotifyUrl(), null);
        SignType signType = configStorage.getSignType();
        this.signType = LangUtils.getOrDefault(this.getSignType(), signType.name());
        
        // 签名
        SignValue signValue = getSignValue();
        String data = signValue.getSignValue();
        Map<String, Object> values = signValue.getHandledValues();
    
        this.sign = signType.sign(data, privateKey);
        values.put("sign", this.sign);
    
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            Object value = entry.getValue();
            entry.setValue(URLEncoder.encode(String.valueOf(value)));
        }
        
        // 访问支付宝接口
        String url = HttpUtils.getUrl(baseUrl, values);
        Converts convert = Converts.valueOf(this.format.toUpperCase());
        JSONObject response = LangKit.getObjectHttpClient().get(url, convert, JSONObject.class);
        String sign = response.getString("sign");
        JSONObject responseString = response.getJSONObject("alipay_trade_create_response");
        Map<String, Object> sortedMap = Maps.newTreeMap();
        responseString.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));
        String jsonString = JSON.toJSONString(sortedMap, SerializerFeature.MapSortField);
    
        boolean verifySign = signType.verify(jsonString, aliPayPublicKey, sign);
    
        if (!verifySign) {
            throw PaymentException.wrap("校验失败");
        }
        
        return convert.convert(jsonString, TradeCreateResponse.class);
    }
}
