package in.hocg.payment.alipay.v2.request.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2019/12/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class SettleInfo {
    
    @JSONField(name = "settle_detail_infos")
    private List<SettleDetailInfo> settleDetailInfos;
    @JSONField(name = "merchant_type")
    private String merchantType;
    
    @Data
    @Accessors(chain = true)
    public static class SettleDetailInfo {
        @JSONField(name = "trans_in_type")
        private String transInType;
        @JSONField(name = "trans_in")
        private String transIn;
        @JSONField(name = "summary_dimension")
        private String summaryDimension;
        @JSONField(name = "settle_entity_id")
        private String settleEntityId;
        @JSONField(name = "settle_entity_type")
        private String settleEntityType;
        @JSONField(name = "amount")
        private String amount;
    }
}
