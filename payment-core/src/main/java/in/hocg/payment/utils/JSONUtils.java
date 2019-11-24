package in.hocg.payment.utils;

import com.alibaba.fastjson.JSON;

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
        return JSON.parseObject(json, beanClass);
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
}
