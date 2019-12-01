package in.hocg.payment.alipay;

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
public class Main {
    
    @Test
    public void testObjectSign() {
        KeyPair keyPair = SignUtils.getKeyPair(1024);
        Base64.Encoder encoder = Base64.getEncoder();
        String privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
    
    }
}
