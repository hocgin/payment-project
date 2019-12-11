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
public class WaitRepaymentOrderInfo {
    @JSONField(name = "advance_order_id")
    private String advanceOrderId;
    @JSONField(name = "alipay_user_id")
    private String alipayUserId;
    @JSONField(name = "orig_biz_order_id")
    private String origBizOrderId;
    @JSONField(name = "biz_product")
    private String bizProduct;
    @JSONField(name = "wait_repayment_amount")
    private String waitRepaymentAmount;
}
