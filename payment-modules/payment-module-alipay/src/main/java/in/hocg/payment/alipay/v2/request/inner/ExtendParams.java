package in.hocg.payment.alipay.v2.request.inner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.payment.sign.SignField;
import lombok.Data;

/**
 * Created by hocgin on 2019/11/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ExtendParams {
    
    @JSONField(name = "sys_service_provider_id")
    @SignField("sys_service_provider_id")
    private String sysServiceProviderId;
    
    @JSONField(name = "industry_reflux_info")
    @SignField("industry_reflux_info")
    private String industryRefluxInfo;
    
    @JSONField(name = "card_type")
    @SignField("card_type")
    private String cardType;
    
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
