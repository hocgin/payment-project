package in.hocg.payment.net;

import in.hocg.payment.utils.JSONUtils;
import in.hocg.payment.utils.XMLUtils;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum Converts implements Convert {
    JSON() {
        @Override
        public <T> T convert(String body, Class<T> clazz) {
            return JSONUtils.toBean(body, clazz);
        }
    },
    XML {
        @Override
        public <T> T convert(String body, Class<T> clazz) {
            return XMLUtils.toBean(body, clazz);
        }
    };
    
}
