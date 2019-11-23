package in.hocg.payment.utils;

import in.hocg.payment.utils.data.XMLBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class XMLUtilsTest {
    
    @Test
    void toBean() {
        XMLBean xmlBean = new XMLBean();
        xmlBean.setKey("键");
        xmlBean.setValue("值");
        String content = XMLUtils.toXml(xmlBean);
        log.debug("内容 {}", content);
    }
    
    @Test
    void toXml() {
        XMLBean xmlBean = new XMLBean();
        xmlBean.setKey("键");
        xmlBean.setValue("值");
        String content = XMLUtils.toXml(xmlBean);
        log.debug("内容 {}", content);
        XMLBean bean = XMLUtils.toBean(content, XMLBean.class);
        log.debug("Bean {}", bean);
    }
}