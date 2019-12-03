package in.hocg.payment.wxpay.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import in.hocg.payment.core.PaymentRequest;

import java.io.Writer;

public class XStreamInitializer {
  
    private static final XppDriver XPP_DRIVER = new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out, getNameCoder()) {
                private static final String PREFIX_CDATA = "<![CDATA[";
                private static final String SUFFIX_CDATA = "]]>";
                
                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
                        writer.write(text);
                    } else {
                        super.writeText(writer, text);
                    }
                    
                }
                
                @Override
                public String encodeNode(String name) {
                    return name;
                }
            };
        }
    };
    
    public static XStream getInstance() {
        XStream xstream = new XStream(new PureJavaReflectionProvider(), XPP_DRIVER);
        xstream.ignoreUnknownElements();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.omitField(PaymentRequest.class, "paymentService");
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(new String[] {
                "in.hocg.**"
        });
        xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
        return xstream;
    }
    
}
