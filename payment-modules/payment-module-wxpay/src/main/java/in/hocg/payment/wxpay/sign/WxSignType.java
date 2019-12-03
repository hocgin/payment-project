package in.hocg.payment.wxpay.sign;

import in.hocg.payment.encrypt.MD5Encrypt;
import in.hocg.payment.sign.strategy.Sign;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum WxSignType implements Sign {
    MD5;
    
    @Override
    public String sign(String data, String privateKey) {
        return MD5Encrypt.encode(data).toUpperCase();
    }
    
    @Override
    public boolean verify(String data, String publicKey, String sign) {
        return sign(data, publicKey).equals(sign);
    }
}
