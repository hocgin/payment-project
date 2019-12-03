package in.hocg.payment.wxpay.xml;


import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class XStreamCDataConverter extends StringConverter {
    
    @Override
    public String toString(Object obj) {
        return "<![CDATA[" + super.toString(obj) + "]]>";
    }
}
