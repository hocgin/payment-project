package in.hocg.payment.parser;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Converter {
    
    <T> T to(String text, Class<T> clazz);
}
