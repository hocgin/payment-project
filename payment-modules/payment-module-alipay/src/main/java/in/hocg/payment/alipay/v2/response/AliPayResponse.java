package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import in.hocg.payment.core.PaymentResponse;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignScheme;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;


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
        final Map<String, String> map = JSON.parseObject(getContent(), new TypeReference<Map<String, String>>() {
        }, Feature.OrderedField);
        String responseKey = "";
        for (String item : map.keySet()) {
            if (item.endsWith("response")) {
                responseKey = item;
                break;
            }
        }
        final String responseBody = map.getOrDefault(responseKey, "{}");
        return scheme.verify(responseBody, key, getSign());
    }
    
}
