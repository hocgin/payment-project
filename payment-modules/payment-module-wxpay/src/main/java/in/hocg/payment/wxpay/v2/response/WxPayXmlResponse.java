package in.hocg.payment.wxpay.v2.response;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.exception.PaymentException;
import in.hocg.payment.sign.SignScheme;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.wxpay.Helpers;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

import static in.hocg.payment.wxpay.constants.Constants.RESPONSE_SUCCESS_CODE;

/**
 * Created by hocgin on 2019/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class WxPayXmlResponse
        extends WxPayResponse {
    @XStreamAlias("return_code")
    private String returnCode;

    @XStreamAlias("return_msg")
    private String returnMsg;

    @XStreamAlias("appid")
    private String appid;

    @XStreamAlias("mch_id")
    private String mchId;

    @XStreamAlias("nonce_str")
    private String nonceStr;

    @XStreamAlias("sign")
    private String sign;

    @XStreamAlias("result_code")
    private String resultCode;

    @XStreamAlias("err_code")
    private String errCode;

    @XStreamAlias("err_code_des")
    private String errCodeDes;

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

    @Override
    public boolean checkSign(SignScheme scheme, String key) {
        Map<String, Object> data = this.toMap();
        SignValue signHelper = Helpers.newSignValue().handle(data);
        String signValue = signHelper.getSignValue();
        signValue += String.format("&key=%s", key);
        return scheme.verify(signValue, key, this.getSign());
    }

    @Override
    public void afterPropertiesSet() {
        // 业务结果检查
        if (!RESPONSE_SUCCESS_CODE.equals(this.getReturnCode())) {
            throw new PaymentException("业务处理失败: " + this.getReturnCode() +": "+ this.returnMsg);
        }
    }
}
