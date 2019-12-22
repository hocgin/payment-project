package in.hocg.payment.encrypt;

import com.google.common.hash.Hashing;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class MD5Encrypt {
    
    /**
     * 签名
     *
     * @param data
     * @return
     */
    public static String encode32(String data) {
        return Hashing.md5().hashBytes(data.getBytes()).toString();
    }
}
