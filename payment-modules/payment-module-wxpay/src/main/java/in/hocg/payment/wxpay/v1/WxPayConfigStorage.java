package in.hocg.payment.wxpay.v1;

import in.hocg.payment.core.ConfigStorage;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class WxPayConfigStorage implements ConfigStorage {
    
    private String appId;
    
    private String mchId;
    
    private String key;

}
