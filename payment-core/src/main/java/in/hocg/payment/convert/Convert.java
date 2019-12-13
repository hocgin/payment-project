package in.hocg.payment.convert;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Convert<T> {
    
    /**
     * 格式转换
     *
     * @param body
     * @param clazz
     * @return
     */
    <R extends T> R convert(String body, Class<R> clazz);
}
