package in.hocg.payment.sign;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import in.hocg.payment.encrypt.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public enum SignType implements SignScheme {
    RSA {
        @Override
        public String sign(String data, String privateKey) {
            return RSAEncrypt.encode(data, privateKey, RSAEncrypt.Algorithm.SHA1WithRSA);
        }

        @Override
        public boolean verify(String data, String publicKey, String sign) {
            return RSAEncrypt.verify(data, publicKey, RSAEncrypt.Algorithm.SHA1WithRSA, sign);
        }
    }, RSA2 {
        @Override
        public String sign(String data, String privateKey) {
            return RSAEncrypt.encode(data, privateKey, RSAEncrypt.Algorithm.SHA256WithRSA);
        }

        @Override
        public boolean verify(String data, String publicKey, String sign) {
            return RSAEncrypt.verify(data, publicKey, RSAEncrypt.Algorithm.SHA256WithRSA, sign);
        }
    },
    SHA256 {
        @Override
        public String sign(String data, String privateKey) {
            String newData = data + privateKey;
            Digester digester = new Digester(DigestAlgorithm.SHA256);
            return digester.digestHex(newData).toUpperCase();
        }

        @Override
        public boolean verify(String data, String publicKey, String sign) {
            return sign(data, publicKey).equalsIgnoreCase(sign);
        }
    };
}
