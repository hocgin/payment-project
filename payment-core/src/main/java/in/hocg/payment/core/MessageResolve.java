package in.hocg.payment.core;

import com.google.common.collect.Maps;
import in.hocg.payment.convert.Convert;
import in.hocg.payment.utils.ClassUtils;

import java.util.Map;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 * 消息转换器
 *
 * @author hocgin
 */
public abstract class MessageResolve<K> {
    /**
     * 规则
     */
    private Map<K, Convert<PaymentMessage>> rules = Maps.newHashMap();
    
    /**
     * 添加解析规则
     *
     * @param key
     * @param convert
     * @return
     */
    public MessageResolve<K> addRule(K key, Convert<PaymentMessage> convert) {
        rules.put(key, convert);
        return this;
    }
    
    /**
     * 解析消息
     *
     * @param key
     * @param body
     * @param <T>
     * @return
     */
    public <T> T resolve(K key, String body) {
        final Convert<PaymentMessage> messageConvert = rules.get(key);
        final Class superclass = ClassUtils.getGenericSuperclass(this.getClass(), 0);
        return ((T) messageConvert.convert(body, superclass));
    }
}
