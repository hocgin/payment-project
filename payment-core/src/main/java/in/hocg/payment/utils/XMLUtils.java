package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class XMLUtils {
    
    /**
     * xml 转为 对象
     *
     * @param xml
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(String xml, Class<T> beanClass) {
        Object object;
        try {
            JAXBContext context = JAXBContext.newInstance(beanClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return ((T) object);
    }
    
    /**
     * Bean 转 xml
     *
     * @param xmlBean
     * @return
     */
    public static String toXml(Object xmlBean) {
        String xmlStr;
        try {
            JAXBContext context = JAXBContext.newInstance(xmlBean.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(xmlBean, writer);
            xmlStr = writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return xmlStr;
    }
    
}
