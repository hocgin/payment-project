package in.hocg.payment.parser.json;

import in.hocg.payment.parser.Converter;
import in.hocg.payment.utils.JSONUtils;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class JsonConverter implements Converter {
    
    @Override
    public <T> T to(String text, Class<T> clazz) {
        return JSONUtils.toBean(text, clazz);
    }
    
}
