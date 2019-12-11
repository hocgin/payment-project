package in.hocg.payment.alipay.v2.request.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class TradeSettleInfo {
    
    @JSONField(name = "trade_settle_detail_list")
    private List<TradeSettleDetail> tradeSettleDetailList;
    
    @Data
    @Accessors(chain = true)
    public static class TradeSettleDetail {
        @JSONField(name = "operation_type")
        private String operationType;
        @JSONField(name = "operation_serial_no")
        private String operationSerialNo;
        @JSONField(name = "operation_dt")
        private String operationDt;
        @JSONField(name = "trans_out")
        private String transOut;
        @JSONField(name = "trans_in")
        private String transIn;
        @JSONField(name = "amount")
        private String amount;
    
    }
}
