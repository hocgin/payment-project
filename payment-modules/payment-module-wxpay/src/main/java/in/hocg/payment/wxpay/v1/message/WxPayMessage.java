package in.hocg.payment.wxpay.v1.message;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import in.hocg.payment.core.PaymentMessage;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.v1.WxPayService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class WxPayMessage extends PaymentMessage<WxPayService> {
    
    private Document xmlDoc;
    
    /**
     * 根据 path 获取 xml 的值
     *
     * @param path
     * @return
     */
    String getXmlValue(String... path) {
        Document doc = this.getXmlDoc();
        String expression = String.format("/%s//text()", Joiner.on("/").join(path));
        try {
            return (String) XPathFactory
                    .newInstance()
                    .newXPath()
                    .compile(expression)
                    .evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 获取xml 文档
     *
     * @return
     */
    private Document getXmlDoc() {
        if (this.xmlDoc != null) {
            return this.xmlDoc;
        }
        
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setExpandEntityReferences(false);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            this.xmlDoc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(this.getContent().getBytes(StandardCharsets.UTF_8)));
            return xmlDoc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * xml 转 Map
     *
     * @return
     */
    public Map<String, Object> toMap() {
        if (Strings.isNullOrEmpty(this.getContent())) {
            throw new RuntimeException("xml内容错误");
        }
        
        Map<String, Object> result = Maps.newHashMap();
        Document doc = this.getXmlDoc();
        
        try {
            NodeList list = (NodeList) XPathFactory.newInstance().newXPath()
                    .compile("/xml/*")
                    .evaluate(doc, XPathConstants.NODESET);
            int len = list.getLength();
            for (int i = 0; i < len; i++) {
                Node node = list.item(i);
                result.put(node.getNodeName(), node.getTextContent());
            }
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        
        return result;
    }
    
    public interface Result extends PaymentMessage.Result {
    
        default String string() {
            return Helpers.newXStream().toXML(this);
        }
    }
    
}
