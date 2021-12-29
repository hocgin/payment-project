package in.hocg.payment.wxpay.sign;

import in.hocg.payment.encrypt.Encrypt;
import in.hocg.payment.encrypt.HMACSHA256Encrypt;
import in.hocg.payment.sign.SignScheme;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum WxSignType implements SignScheme {
    MD5 {
        @Override
        public String string() {
            return "MD5";
        }

        @Override
        public String sign(String data, String privateKey) {
            return Encrypt.MD5_32.encrypt(data, Encrypt.Option.md5()).toUpperCase();
        }

        @Override
        public boolean verify(String data, String publicKey, String sign) {
            return sign(data, publicKey).equals(sign);
        }
    }, HMAC_SHA256 {
        @Override
        public String string() {
            return "HMAC-SHA256";
        }

        @Override
        public String sign(String data, String privateKey) {
            return HMACSHA256Encrypt.encode(data, privateKey).toUpperCase();
        }

        @Override
        public boolean verify(String data, String publicKey, String sign) {
            return sign(data, publicKey).equals(sign);
        }
    };

    public static WxSignType of(String name) {
        for (WxSignType value : WxSignType.values()) {
            if (value.string().equals(name)) {
                return value;
            }
        }
        return WxSignType.HMAC_SHA256;
    }
}
