package in.hocg.payment.alipay.v2.request.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/12/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class LogisticsDetail {
    @JSONField(name = "logistics_type")
    private String logisticsType;
}
