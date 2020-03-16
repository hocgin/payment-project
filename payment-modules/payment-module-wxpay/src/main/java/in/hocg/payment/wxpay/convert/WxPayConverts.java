package in.hocg.payment.wxpay.convert;

import com.thoughtworks.xstream.XStream;
import in.hocg.payment.convert.StringConvert;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.v2.message.WxPayMessage;
import in.hocg.payment.wxpay.v2.response.WxPayDataResponse;
import in.hocg.payment.wxpay.v2.response.WxPayXmlResponse;

/**
 * Created by hocgin on 2019/12/10.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public final class WxPayConverts {
    public static final StringConvert<WxPayXmlResponse> XML = new StringConvert<WxPayXmlResponse>() {
        @Override
        public <R extends WxPayXmlResponse> R convert(String body, Class<R> clazz) {
            XStream xstream = Helpers.newXStream();
            xstream.processAnnotations(clazz);
            return (R) xstream.fromXML(body);
        }
    };

    public static final StringConvert<WxPayDataResponse> TEXT = new StringConvert<WxPayDataResponse>() {
        @Override
        public <R extends WxPayDataResponse> R convert(String body, Class<R> clazz) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public static final StringConvert<WxPayMessage> MESSAGE = new StringConvert<WxPayMessage>() {
        @Override
        public <R extends WxPayMessage> R convert(String body, Class<R> clazz) {
            XStream xstream = Helpers.newXStream();
            xstream.processAnnotations(clazz);
            return (R) xstream.fromXML(body);
        }
    };

}
