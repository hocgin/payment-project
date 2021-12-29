package in.hocg.payment.encrypt;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import lombok.Getter;

/**
 * Created by hocgin on 2021/12/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum Encrypt {
    // 非对称加密
    RSA_SHA1 {
        @Override
        public String encrypt(String str, Option option) {
            return Base64Encoder.encode(SecureUtil.sign(SignAlgorithm.SHA1withRSA, option.getPrivateKeyBase64(), option.getPublicKeyBase64()).sign(str.getBytes()));
        }

        @Override
        public String decrypt(String data, Option option) {
            return null;
        }
    },
    // 摘要算法 lower_case
    MD5_32 {
        @Override
        public String encrypt(String str, Option option) {
            return SecureUtil.md5(str);
        }

        @Override
        public String decrypt(String data, Option option) {
            return SecureUtil.md5(data);
        }
    },
    // 对称加密
    AES {
        @Override
        public String encrypt(String str, Option option) {
            return SecureUtil.aes(option.getKey().getBytes()).encryptBase64(str);
        }

        @Override
        public String decrypt(String data, Option option) {
            return SecureUtil.aes(option.getKey().getBytes()).decryptStr(data);
        }
    };

    /**
     * 加密
     *
     * @param str
     * @param option
     * @return
     */
    public String encrypt(String str, Option option) {
        throw new UnsupportedOperationException("不支持该加密方式");
    }

    /**
     * 解密
     *
     * @param data
     * @param option
     * @return
     */
    public String decrypt(String data, Option option) {
        throw new UnsupportedOperationException("不支持该加密方式");
    }

    @Getter
    public static class Option {
        /**
         * 对称加密_key
         */
        private String key;
        /**
         * 非对称加密_私钥
         */
        private String privateKeyBase64;
        /**
         * 非对称加密_公钥
         */
        private String publicKeyBase64;

        private static Option create() {
            return new Option();
        }

        public static Option md5() {
            return new Option();
        }

        public static Option rsa(String privateKeyBase64, String publicKeyBase64) {
            Option option = create();
            option.privateKeyBase64 = privateKeyBase64;
            option.publicKeyBase64 = publicKeyBase64;
            return option;
        }

        public static Option aes(String key) {
            Option option = create();
            option.key = key;
            return option;
        }
    }
}
