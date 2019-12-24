package in.hocg.payment.convert;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Convert<B, T> {
    /**
     * 格式转换
     *
     * @param basic
     * @param clazz
     * @return
     */
    <R extends T> R convert(B basic, Class<R> clazz);
}
