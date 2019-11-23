package in.hocg.payment.sign.data;

import in.hocg.payment.sign.SignField;
import lombok.Data;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class WaitSignObject extends SuperSignObject {
    @SignField
    private final String key;
    @SignField
    private final Integer vInt;
    @SignField
    private final String nil;
    
    private String sign;
    
    public WaitSignObject(String superVal, String key, Integer vInt, String nil) {
        super(superVal);
        this.key = key;
        this.vInt = vInt;
        this.nil = nil;
    }
}
