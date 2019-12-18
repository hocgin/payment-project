package in.hocg.payment.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * Created by hocgin on 2019/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AES256Encrypt {
    public static final String ALGORITHM = "AES/ECB/PKCS7Padding";
    
    public static byte[] encode(String content,
                                String key) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(content.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String decode(byte[] content,
                                String key) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] data = cipher.doFinal(content);
            return new String(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
