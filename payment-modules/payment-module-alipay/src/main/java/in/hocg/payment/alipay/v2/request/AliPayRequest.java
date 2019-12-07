package in.hocg.payment.alipay.v2.request;

import in.hocg.payment.alipay.sign.Helpers;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.core.PaymentRequest;
import in.hocg.payment.core.PaymentResponse;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignObjects;
import in.hocg.payment.sign.SignValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public abstract class AliPayRequest<R extends PaymentResponse>
        extends PaymentRequest<AliPayService, R> {
    
    @ApiField(value = "app_id", required = true)
    protected String appId;
    
    @ApiField(value = "method", required = true)
    protected String method;
    
    @ApiField(value = "format", required = true)
    protected String format;
    
    @ApiField(value = "charset", required = true)
    protected String charset;
    
    @ApiField(value = "sign_type", required = true)
    protected String signType;
    
    @ApiField(value = "sign", ignore = true)
    protected String sign;
    
    @ApiField(value = "timestamp", required = true)
    protected String timestamp;
    
    @ApiField(value = "version", required = true)
    protected String version;
    
    @ApiField("notify_url")
    protected String notifyUrl;
    
    @ApiField("app_auth_token")
    protected String appAuthToken;
    
    @ApiField(value = "biz_content", required = true)
    protected String bizContent = "{}";
    
    
    protected SignValue getSignValue() {
        Map<String, Object> values = SignObjects.getSignValues(this);
        return Helpers.newSignValue().handle(values);
    }
}
