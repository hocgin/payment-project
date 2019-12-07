package in.hocg.payment.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class JSONUtils {
    
    /**
     * JSON 转 对象
     *
     * @param json
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(String json, Class<T> beanClass) {
        return JSON.parseObject(json, beanClass, Feature.OrderedField);
    }
    
    /**
     * 对象 转 JSON
     *
     * @param bean
     * @return
     */
    public static String toJSON(Object bean) {
        return JSON.toJSONString(bean);
    }
    
    /**
     * 美化 JSON
     *
     * @param object
     * @return
     */
    public static String pretty(Object object) {
        if (object == null) {
            return "";
        }
        
        Object jsonObject = object;
        if (object instanceof String) {
            jsonObject = JSON.parseObject(((String) object));
        }
        return JSON.toJSONString(jsonObject, true);
    }
}
