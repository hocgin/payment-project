package in.hocg.payment.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class PaymentMap {
    @JSONField(unwrapped = true)
    private final Map<String, Object> map;
    
    public PaymentMap() {
        this(new HashMap<>());
    }
    
    public PaymentMap(Map<String, Object> map) {
        this.map = map;
    }
    
    public void set(String key, Object value) {
        map.put(key, value);
    }
    
    public String toJSONString() {
        return JSON.toJSONString(this);
    }
    
}
