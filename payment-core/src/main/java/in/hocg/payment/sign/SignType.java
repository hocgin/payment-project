package in.hocg.payment.sign;

import in.hocg.payment.encrypt.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public enum SignType implements Sign {
    //
    RSA {
        @Override
        public String sign(String data, String privateKey) {
            return RSAEncrypt.sign(data, privateKey, RSAEncrypt.Algorithm.SHA1WithRSA);
        }
        
        @Override
        public boolean verify(String data, String publicKey, String sign) {
            return RSAEncrypt.verify(data, publicKey, RSAEncrypt.Algorithm.SHA1WithRSA, sign);
        }
    }, RSA2 {
        @Override
        public String sign(String data, String privateKey) {
            return RSAEncrypt.sign(data, privateKey, RSAEncrypt.Algorithm.SHA256WithRSA);
        }
        
        @Override
        public boolean verify(String data, String publicKey, String sign) {
            return RSAEncrypt.verify(data, publicKey, RSAEncrypt.Algorithm.SHA256WithRSA, sign);
        }
    };
}
