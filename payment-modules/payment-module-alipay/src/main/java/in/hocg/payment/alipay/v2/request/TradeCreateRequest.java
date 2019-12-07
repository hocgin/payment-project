package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.JSONObject;
import in.hocg.payment.alipay.Helpers;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.response.TradeCreateResponse;
import in.hocg.payment.net.Converts;
import in.hocg.payment.sign.SignType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

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
        // 设置参数
        setPublicValues();
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        @NonNull String privateKey = configStorage.getPrivateKey();
        @NonNull String aliPayPublicKey = configStorage.getAliPayPublicKey();
        SignType signType = configStorage.getSignType();
        String baseUrl = configStorage.getUrl();
        this.method = "alipay.trade.create";
        
        // 处理签名
        Map<String, Object> values = setSignAndGetParams(privateKey, signType);
        
        // 请求支付宝接口
        String url = Helpers.getUrl(baseUrl, values);
        Converts convert = Converts.valueOf(this.format.toUpperCase());
        JSONObject response = Helpers.getObjectHttpClient().get(url, convert, JSONObject.class);
        
        // 处理响应结果
        return wrapResponse(response, "alipay_trade_create_response",
                signType,
                aliPayPublicKey,
                convert,
                TradeCreateResponse.class);
    }
}
