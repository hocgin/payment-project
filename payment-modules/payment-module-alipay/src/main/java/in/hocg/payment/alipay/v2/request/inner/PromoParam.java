package in.hocg.payment.alipay.v2.request.inner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.sign.ApiField;
import lombok.Data;

/**
 * Created by hocgin on 2019/11/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class PromoParam {
    
    @JSONField(name = "actual_order_time")
    @ApiField("actual_order_time")
    private String actualOrderTime;
    
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
