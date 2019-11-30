package in.hocg.payment.sign.data;

import in.hocg.payment.sign.ApiField;
import lombok.Data;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public abstract class SuperSignObject {
    
    @ApiField
    private String superVal;
}
