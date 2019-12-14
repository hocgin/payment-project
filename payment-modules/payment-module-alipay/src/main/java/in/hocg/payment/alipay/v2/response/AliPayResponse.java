package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.core.PaymentResponse;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignScheme;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AliPayResponse extends PaymentResponse {
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
    
    @Override
    public boolean checkSign(SignScheme scheme, String key) {
        final String response = "response";
        final String content = getContent();
        final int beginIndex = content.indexOf(response) + response.length() + 2;
        final int endIndex =content.indexOf(",\"sign\"");
        if (endIndex < 0) {
            return false;
        }
        final String responseBody = content.substring(beginIndex, endIndex);
        return scheme.verify(responseBody, key, getSign());
    }
    
}
