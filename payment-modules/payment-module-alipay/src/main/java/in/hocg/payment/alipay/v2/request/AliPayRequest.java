package in.hocg.payment.alipay.v2.request;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.AliPayConfigStorage;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.core.AbsPaymentRequest;
import in.hocg.payment.core.PaymentResponse;
import in.hocg.payment.sign.ApiField;
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
public abstract class AliPayRequest<R extends PaymentResponse>
        extends AbsPaymentRequest<AliPayService, R> {
    
    @JSONField(name = "app_id")
    @ApiField(value = "app_id", required = true)
    protected String appId;
    
    @JSONField(name = "method")
    @ApiField(value = "method", required = true)
    protected String method;
    
    @JSONField(name = "format")
    @ApiField(value = "format", required = true)
    protected String format = "JSON";
    
    @JSONField(name = "charset")
    @ApiField(value = "charset", required = true)
    protected String charset;
    
    @JSONField(name = "sign_type")
    @ApiField(value = "sign_type", required = true)
    protected String signType;
    
    @JSONField(name = "sign")
    @ApiField(value = "sign", ignore = true)
    protected String sign;
    
    @JSONField(name = "timestamp")
    @ApiField(value = "timestamp", required = true)
    protected String timestamp;
    
    @JSONField(name = "version")
    @ApiField(value = "version", required = true)
    protected String version = "1";
    
    @JSONField(name = "notify_url")
    @ApiField("notify_url")
    protected String notifyUrl;
    
    @JSONField(name = "app_auth_token")
    @ApiField("app_auth_token")
    protected String appAuthToken;
    
    @JSONField(name = "biz_content")
    @ApiField(value = "biz_content", required = true)
    protected String bizContent = "{}";
    
    @Override
    protected String sign() {
        AliPayConfigStorage configStorage = getPaymentService().getConfigStorage();
        return null;
    }
}
