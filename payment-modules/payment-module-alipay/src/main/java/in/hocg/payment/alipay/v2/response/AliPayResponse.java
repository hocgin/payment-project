package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.core.PaymentResponse;
import in.hocg.payment.sign.ApiField;
import lombok.Data;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public abstract class AliPayResponse implements PaymentResponse {
    
    @JSONField(name = "code")
    @ApiField(value = "code", required = true)
    private String code;
    
    @JSONField(name = "msg")
    @ApiField(value = "msg", required = true)
    private String msg;
    
    @JSONField(name = "sign")
    @ApiField(value = "sign", required = true, ignore = true)
    private String sign;
    
    @JSONField(name = "sub_code")
    @ApiField(value = "sub_code")
    private String subCode;
    
    @JSONField(name = "sub_msg")
    @ApiField(value = "sub_msg")
    private String subMsg;
    
}
