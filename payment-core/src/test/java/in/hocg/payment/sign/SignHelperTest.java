package in.hocg.payment.sign;

import in.hocg.payment.sign.data.WaitSignObject;
import in.hocg.payment.sign.strategy.DefaultSignStrategy;
import in.hocg.payment.utils.SignUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.util.Base64;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class SignHelperTest {
    
    @Test
    void getSignString() {
        KeyPair keyPair = SignUtils.getKeyPair(1024);
        Base64.Encoder encoder = Base64.getEncoder();
        String privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
        
        WaitSignObject object = new WaitSignObject("superVal", "keyValue", 12, null);
        SignType rsa = SignType.RSA;
        String signString = DefaultSignStrategy.getSignString(object, privateKey, rsa);
        boolean result = DefaultSignStrategy.verifySign(object, publicKey, rsa, signString);
        Assertions.assertTrue(result);
    }
    
}