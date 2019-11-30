package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.core.PaymentResponse;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hocgin on 2019/11/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
public class WrapperResponse<T extends AliPayResponse> implements PaymentResponse {
    
    @JSONField(name = "alipay_trade_pay_response")
    private T alipayTradePayResponse;
    
    @JSONField(name = "sign")
    private String sign;
}
