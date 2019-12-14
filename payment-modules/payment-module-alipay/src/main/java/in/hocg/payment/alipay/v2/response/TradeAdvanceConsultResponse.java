package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.alipay.v2.request.item.WaitRepaymentOrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeAdvanceConsultResponse extends AliPayHttpResponse {
    @JSONField(name = "refer_result")
    private String referResult;
    @JSONField(name = "wait_repayment_order_infos")
    private WaitRepaymentOrderInfo waitRepaymentOrderInfo;
    @JSONField(name = "wait_repayment_amount")
    private String waitRepaymentAmount;
    @JSONField(name = "wait_repayment_order_count")
    private String waitRepaymentOrderCount;
    @JSONField(name = "risk_level")
    private String riskLevel;
    @JSONField(name = "result_message")
    private String resultMessage;
    @JSONField(name = "result_code")
    private String resultCode;
}
