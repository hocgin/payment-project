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
public class TradeFundBill {
    @JSONField(name = "fund_channel")
    private String fundChannel;
    @JSONField(name = "bank_code")
    private String bankCode;
    @JSONField(name = "amount")
    private String amount;
    @JSONField(name = "real_amount")
    private String realAmount;
}
