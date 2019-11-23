package in.hocg.payment.utils;

import java.util.Objects;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class LangUtils {
    
    /**
     * 如果 Null 取默认值
     * @param val
     * @param def
     * @param <T>
     * @return
     */
    public static  <T> T getOrDefault(T val, T def) {
        return Objects.isNull(val) ? def : val;
    }
}
