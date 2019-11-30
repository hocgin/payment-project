package in.hocg.payment.parser;

import in.hocg.payment.sign.data.WaitSignObject;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class ConvertersTest {
    
    @Test
    void convert() {
        WaitSignObject object = new WaitSignObject("superVal", "keyValue", 12, null);
    }
}