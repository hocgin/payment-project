package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class StringUtils {
    
    /**
     * 字符串比较
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        
        return s1 != null && s1.equals(s2);
    }
}
