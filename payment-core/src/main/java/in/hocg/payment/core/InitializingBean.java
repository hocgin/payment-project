package in.hocg.payment.core;

import in.hocg.payment.convert.Convert;

/**
 * Created by hocgin on 2019/12/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface InitializingBean {
    
    /**
     * 初始化后调用
     */
    void afterPropertiesSet();
    
    /**
     * 内容
     *
     * @param content
     */
    void setContent(String content);
    
    /**
     * 字符串解析为对象
     *
     * @param convert
     * @param content
     * @param clazz
     * @param <T>
     * @return
     */
    static <T extends InitializingBean> T from(Convert convert,
                                               String content,
                                               Class<T> clazz) {
        T object = (T) convert.convert(content, clazz);
        object.setContent(content);
        object.afterPropertiesSet();
        return object;
    }
}
