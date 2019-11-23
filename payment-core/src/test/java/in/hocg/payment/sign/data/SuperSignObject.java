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
public abstract class SuperSignObject {
    
    @SignField
    private final String superVal;
}
