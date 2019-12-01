package in.hocg.payment.net;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Convert {
    
    /**
     * 格式转换
     *
     * @param body
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T convert(String body, Class<T> clazz);
}
