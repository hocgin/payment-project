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
    public static final String FIELD_CODE = "code";
    public static final String FIELD_MSG = "msg";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SUB_CODE = "sub_code";
    public static final String FIELD_SUB_MSG = "sub_msg";
    
    @JSONField(name = AliPayResponse.FIELD_CODE)
    @ApiField(value = AliPayResponse.FIELD_CODE, required = true)
    private String code;
    
    @JSONField(name = AliPayResponse.FIELD_MSG)
    @ApiField(value = AliPayResponse.FIELD_MSG, required = true)
    private String msg;
    
    @JSONField(name = AliPayResponse.FIELD_SIGN)
    @ApiField(value = AliPayResponse.FIELD_SIGN, required = true, ignore = true)
    private String sign;
    
    @JSONField(name = AliPayResponse.FIELD_SUB_CODE)
    @ApiField(value = AliPayResponse.FIELD_SUB_CODE)
    private String subCode;
    
    @JSONField(name = AliPayResponse.FIELD_SUB_MSG)
    @ApiField(value = AliPayResponse.FIELD_SUB_MSG)
    private String subMsg;
    
}
