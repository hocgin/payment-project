package in.hocg.payment.parser.xml;

import in.hocg.payment.parser.Converter;
import in.hocg.payment.utils.XMLUtils;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class XmlConverter implements Converter {
    
    @Override
    public <T> T to(String text, Class<T> clazz) {
        return XMLUtils.toBean(text, clazz);
    }
    
}
