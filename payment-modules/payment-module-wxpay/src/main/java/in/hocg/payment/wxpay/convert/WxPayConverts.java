package in.hocg.payment.wxpay.convert;

import com.thoughtworks.xstream.XStream;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.v1.response.WxPayResponse;

/**
 * Created by hocgin on 2019/12/10.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum WxPayConverts implements Convert<WxPayResponse> {
    XML{
        @Override
        public <T extends WxPayResponse> T convert(String body, Class<T> clazz) {
            XStream xstream = Helpers.newXStream();
            xstream.processAnnotations(clazz);
            return (T) xstream.fromXML(body);
        }
    }
}
