package in.hocg.payment.parser;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@XmlRootElement(name = "koubei_trade_itemorder_query_response")
public class AliPayXMLObject {
    
    @XmlElement(name = "code")
    private Integer code;
    
    @XmlElementWrapper(name = "item_order_vo")
    @XmlElement(name = "item_order_v_o")
    private List<Item> itemOrderVo = Lists.newArrayList();
    
    public static class Item {
        @XmlElement(name = "item_order_no")
        private String itemOrderNo;
    }
}
