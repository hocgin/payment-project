package in.hocg.payment.wxpay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.net.HttpClient;
import in.hocg.payment.net.HttpClientFactory;
import in.hocg.payment.net.OkHttpClient;
import in.hocg.payment.sign.SignValue;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import in.hocg.payment.wxpay.net.CertHttpClient;
import lombok.extern.slf4j.Slf4j;

import java.io.Writer;
import java.util.Objects;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class Helpers {
    private static HttpClient certHttpClient = null;

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

    /**
     * XStream
     *
     * @return
     */
    public static XStream newXStream() {
        XStream xstream = new XStream(new PureJavaReflectionProvider(), XPP_DRIVER);
        xstream.ignoreUnknownElements();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.omitField(PaymentRequest.class, "paymentService");
        xstream.autodetectAnnotations(true);
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(new String[]{
            "in.hocg.**"
        });
        xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
        return xstream;
    }

    /**
     * 微信签名策略
     */
    public static SignValue newSignValue() {
        SignValue signValue = new SignValue();
        return signValue.setFilter(entry -> Objects.nonNull(entry.getValue()))
            .setOrderStrategy(SignValue.KeyOrder.ASC);
    }

    public static HttpClient getHttpClient() {
        return HttpClientFactory.getSingleInstance(OkHttpClient.class);
    }

    public static HttpClient getCertHttpClient(WxPayConfigStorage configStorage) {
        if (certHttpClient == null) {
            certHttpClient = new CertHttpClient(configStorage);
        }
        return certHttpClient;
    }
}
