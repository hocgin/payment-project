package in.hocg.payment.sign;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SignScheme {
    
    /**
     * 签名
     *
     * @param data
     * @param privateKey
     * @return
     */
    String sign(String data, String privateKey);
    
    /**
     * 校验签名
     *
     * @param data
     * @param publicKey
     * @param sign
     * @return
     */
    boolean verify(String data, String publicKey, String sign);
    
    /**
     * 签名算法名称
     *
     * @return
     */
    default String string() {
        if (this instanceof Enum) {
            return ((Enum) this).name();
        }
        return this.getClass().getSimpleName();
    }
    
    default SignScheme useLogger() {
        return new SignSchemeLogger(this);
    }
}
