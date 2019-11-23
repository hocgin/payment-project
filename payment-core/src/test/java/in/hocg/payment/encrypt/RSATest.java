package in.hocg.payment.encrypt;

import in.hocg.payment.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.util.Base64;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class RSATest {
    
    /**
     * 验证 RSA 签名算法
     */
    @Test
    void testRSA1() {
        // 编码
        Base64.Encoder encoder = Base64.getEncoder();
        KeyPair keyPair = SignUtils.getKeyPair(2048);
        String privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
        log.debug("私钥: {}", privateKey);
        log.debug("公钥: {}", publicKey);
        
        String text = "as";
        String sign = RSAEncrypt.sign(text, privateKey, RSAEncrypt.Algorithm.SHA1WithRSA);
        log.debug("签名结果: {}", sign);
        boolean result = RSAEncrypt.verify(text, publicKey, RSAEncrypt.Algorithm.SHA1WithRSA, sign);
        log.debug("验证签名结果: {}", result);
    }
    
    @Test
    void testRSA2() {
        // 编码
        Base64.Encoder encoder = Base64.getEncoder();
        KeyPair keyPair = SignUtils.getKeyPair(2048);
        String privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
        log.debug("私钥: {}", privateKey);
        log.debug("公钥: {}", publicKey);
        
        String text = "as";
        String sign = RSAEncrypt.sign(text, privateKey, RSAEncrypt.Algorithm.SHA256WithRSA);
        log.debug("签名结果: {}", sign);
        boolean result = RSAEncrypt.verify(text, publicKey, RSAEncrypt.Algorithm.SHA256WithRSA, sign);
        log.debug("验证签名结果: {}", result);
    }
    
}