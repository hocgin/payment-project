package in.hocg.payment.resolve;

import in.hocg.payment.convert.StringConvert;
import lombok.NonNull;

import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class StringResolve<K> extends DataResolve<String, K> {
    
    public static class StringRule<T, R> extends Rule<String, T, R> {
        public StringRule(@NonNull StringConvert convert,
                          @NonNull Function<? extends T, R> handle) {
            super(convert, handle);
        }
    }
}
