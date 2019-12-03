package in.hocg.payment.parser;

import in.hocg.payment.sign.data.WaitSignObject;
import in.hocg.payment.utils.XMLUtils;
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
    
    @Test
    void testAliPayXmlObject() {
        AliPayXMLObject bean = XMLUtils.toBean(Data.XML_DATA_ALIPAY, AliPayXMLObject.class);
        System.out.println(bean);
    }
}