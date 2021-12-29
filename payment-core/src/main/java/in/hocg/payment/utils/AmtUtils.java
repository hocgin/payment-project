package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class AmtUtils {

    /**
     * 元
     *
     * @param yuan
     * @return
     */
    public static String toStr(BigDecimal yuan, int scale) {
        return yuan.setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 元
     *
     * @param yuan
     * @return
     */
    public static String toStr(BigDecimal yuan) {
        return toStr(yuan, 2);
    }

}
