package in.hocg.payment.core;

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
    
}
