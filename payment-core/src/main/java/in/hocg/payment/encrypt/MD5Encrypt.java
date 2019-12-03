package in.hocg.payment.encrypt;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class MD5Encrypt {
    
    /**
     * 签名
     *
     * @param data
     * @return
     */
    public static String encode(String data) {
        String sign = Hashing.md5().hashBytes(data.getBytes()).toString();
        log.debug("待签名的数据: {}\n生成的签名: {}", data, sign);
        return sign;
    }
}
