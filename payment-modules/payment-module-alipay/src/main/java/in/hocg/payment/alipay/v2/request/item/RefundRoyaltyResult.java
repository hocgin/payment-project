package in.hocg.payment.alipay.v2.request.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RefundRoyaltyResult {
    @JSONField(name = "refund_amount")
    private String refundAmount;
    @JSONField(name = "royalty_type")
    private String royaltyType;
    @JSONField(name = "result_code")
    private String resultCode;
    @JSONField(name = "trans_out")
    private String transOut;
    @JSONField(name = "trans_out_email")
    private String transOutEmail;
    @JSONField(name = "trans_in")
    private String transIn;
    @JSONField(name = "trans_in_email")
    private String transInEmail;
}
