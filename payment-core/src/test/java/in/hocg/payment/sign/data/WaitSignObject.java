package in.hocg.payment.sign.data;

import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@NoArgsConstructor
public class WaitSignObject extends SuperSignObject {
    @ApiField
    private String key;
    @ApiField
    private Integer vInt;
    @ApiField
    private String nil;
    
    private String sign;
    
    public WaitSignObject(String superVal, String key, Integer vInt, String nil) {
        this.setSuperVal(superVal);
        this.key = key;
        this.vInt = vInt;
        this.nil = nil;
    }
}
