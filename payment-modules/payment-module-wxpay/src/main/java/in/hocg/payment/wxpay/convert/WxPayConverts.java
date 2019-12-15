package in.hocg.payment.wxpay.convert;

import com.thoughtworks.xstream.XStream;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.v1.response.WxPayDataResponse;
import in.hocg.payment.wxpay.v1.response.WxPayXmlResponse;

/**
 * Created by hocgin on 2019/12/10.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public final class WxPayConverts {
    public static final Convert<WxPayXmlResponse> XML = new Convert<WxPayXmlResponse>() {
        @Override
        public <R extends WxPayXmlResponse> R convert(String body, Class<R> clazz) {
            XStream xstream = Helpers.newXStream();
            xstream.processAnnotations(clazz);
            return (R) xstream.fromXML(body);
        }
    };
    
    public static final Convert<WxPayDataResponse> TEXT = new Convert<WxPayDataResponse>() {
        @Override
        public <R extends WxPayDataResponse> R convert(String body, Class<R> clazz) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    };
    
}
