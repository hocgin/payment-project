package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.core.PaymentResponse;
import lombok.NonNull;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class AliPayResponse implements PaymentResponse {
    
    @NonNull
    @JSONField(name = "code")
    private String code;
    
    @NonNull
    @JSONField(name = "msg")
    private String msg;
    
    @JSONField(name = "sub_code")
    private String subCode;
    
    @JSONField(name = "sub_msg")
    private String subMsg;
    
    @NonNull
    @JSONField(name = "sign")
    private String sign;

}
