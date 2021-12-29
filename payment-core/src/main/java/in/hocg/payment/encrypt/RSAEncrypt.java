package in.hocg.payment.encrypt;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class RSAEncrypt {

    @Getter
    @RequiredArgsConstructor
    public
    enum Algorithm {
        // RSA
        SHA1WithRSA("SHA1WithRSA"),
        // RSA2
        SHA256WithRSA("SHA256WithRSA");
        private final String value;
    }

    private static final String KEY_ALGORITHM = "RSA";

    public static String encode(@NonNull String data, @NonNull String privateKey, @NonNull Algorithm algorithm) {
        try {
            Signature signature = Signature.getInstance(algorithm.getValue());
            signature.initSign(getPrivateKey(privateKey));
            signature.update(data.getBytes());
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(@NonNull String data,
                                 @NonNull String publicKey,
                                 @NonNull Algorithm algorithm,
                                 @NonNull String sign) {
        try {
            Signature signature = Signature.getInstance(algorithm.getValue());
            signature.initVerify(getPublicKey(publicKey));
            signature.update(data.getBytes());
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从字符串中获取公钥
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(publicKey);
        KeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从字符串中获取私钥
     *
     * @param privateKey
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(privateKey);
        KeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }
}
