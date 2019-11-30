package in.hocg.payment.parser;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Parser<T> {
    
    /**
     * 解析为指定对象
     *
     * @param text
     * @return
     */
    T parse(String text);
    
    /**
     * 解析目标类型
     *
     * @return
     */
    Class<T> getTargetClass();
}
