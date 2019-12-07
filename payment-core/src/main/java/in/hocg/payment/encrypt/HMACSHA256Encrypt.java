package in.hocg.payment.encrypt;

import lombok.experimental.UtilityClass;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by hocgin on 2019/12/4.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class HMACSHA256Encrypt {
    /**
     * 字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }
    
    /**
     * 编码
     *
     * @param data   数据
     * @param secret 秘钥
     * @return 加密后字符串
     */
    public static String sign(String data, String secret) {
        String hash;
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256HMAC.init(secretKey);
            byte[] bytes = sha256HMAC.doFinal(data.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hash;
    }
}
