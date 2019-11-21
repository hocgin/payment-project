package in.hocg.payment.core;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Help {
    
    /**
     * 帮助文档
     */
    default void help() {
        throw new UnsupportedOperationException(this.getClass().getName() + " 未实现 help() 函数");
    }
    
}
