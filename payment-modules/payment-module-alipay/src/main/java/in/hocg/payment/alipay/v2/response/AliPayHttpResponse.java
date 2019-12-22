package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.exception.ExceptionFactory;
import in.hocg.payment.exception.PaymentException;
import in.hocg.payment.alipay.constants.Constants;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignScheme;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/12/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AliPayHttpResponse extends AliPayResponse {
    
    public static final String FIELD_CODE = "code";
    public static final String FIELD_MSG = "msg";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SUB_CODE = "sub_code";
    public static final String FIELD_SUB_MSG = "sub_msg";
    
    @JSONField(name = AliPayHttpResponse.FIELD_CODE)
    @ApiField(value = AliPayHttpResponse.FIELD_CODE, required = true)
    private String code;
    
    @JSONField(name = AliPayHttpResponse.FIELD_MSG)
    @ApiField(value = AliPayHttpResponse.FIELD_MSG, required = true)
    private String msg;
    
    @JSONField(name = AliPayHttpResponse.FIELD_SIGN)
    @ApiField(value = AliPayHttpResponse.FIELD_SIGN, required = true, ignore = true)
    private String sign;
    
    @JSONField(name = AliPayHttpResponse.FIELD_SUB_CODE)
    @ApiField(value = AliPayHttpResponse.FIELD_SUB_CODE)
    private String subCode;
    
    @JSONField(name = AliPayHttpResponse.FIELD_SUB_MSG)
    @ApiField(value = AliPayHttpResponse.FIELD_SUB_MSG)
    private String subMsg;
    
    @Override
    public boolean checkSign(SignScheme scheme, String key) {
        final String response = "response";
        final String content = getContent();
        final int beginIndex = content.indexOf(response) + response.length() + 2;
        final int endIndex = content.indexOf(",\"sign\"");
        if (endIndex < 0) {
            return false;
        }
        final String responseBody = content.substring(beginIndex, endIndex);
        return scheme.verify(responseBody, key, getSign());
    }
    
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        // 如果业务处理失败
        if (!Constants.RESPONSE_SUCCESS_CODE.equals(this.getCode())) {
            throw ExceptionFactory.wrap("业务处理失败，错误码=" + this.getCode());
        }
    }
}
