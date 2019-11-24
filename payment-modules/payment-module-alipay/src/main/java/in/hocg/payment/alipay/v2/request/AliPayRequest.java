package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.response.AppPayResponse;
import in.hocg.payment.core.AbsPaymentRequest;
import in.hocg.payment.sign.strategy.DefaultSignStrategy;
import in.hocg.payment.sign.SignField;
import in.hocg.payment.sign.SignType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AliPayRequest
        extends AbsPaymentRequest<AliPayService, AppPayResponse> {
    
    @JSONField(name = "app_id")
    @SignField(value = "app_id", required = true)
    protected String appId;
    
    @JSONField(name = "method")
    @SignField(value = "method", required = true)
    protected String method;
    
    @JSONField(name = "format")
    @SignField(value = "format", required = true)
    protected String format = "JSON";
    
    @JSONField(name = "charset")
    @SignField(value = "charset", required = true)
    protected String charset;
    
    @JSONField(name = "sign_type")
    @SignField(value = "sign_type", required = true)
    protected String signType;
    
    @JSONField(name = "sign")
    @SignField(value = "sign", ignore = true)
    protected String sign;
    
    @JSONField(name = "timestamp")
    @SignField(value = "timestamp", required = true)
    protected String timestamp;
    
    @JSONField(name = "version")
    @SignField(value = "version", required = true)
    protected String version = "1";
    
    @JSONField(name = "notify_url")
    @SignField("notify_url")
    protected String notifyUrl;
    
    @JSONField(name = "app_auth_token")
    @SignField("app_auth_token")
    protected String appAuthToken;
    
    @JSONField(name = "biz_content")
    @SignField(value = "biz_content", required = true)
    protected String bizContent = "{}";
    
    @Override
    protected String sign() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        return DefaultSignStrategy.getSignString(this, configStorage.getPrivateKey(), SignType.valueOf(getSignType()));
    }
}
