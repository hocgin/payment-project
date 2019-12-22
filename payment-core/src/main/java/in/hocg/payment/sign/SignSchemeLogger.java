package in.hocg.payment.sign;

import in.hocg.payment.utils.LangUtils;
import in.hocg.payment.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.StringJoiner;

/**
 * Created by hocgin on 2019/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class SignSchemeLogger implements SignScheme {
    private final SignScheme scheme;
    
    public SignSchemeLogger(SignScheme scheme) {
        this.scheme = scheme;
    }
    
    @Override
    public String sign(String data, String privateKey) {
        String result = null;
        try {
            result = scheme.sign(data, privateKey);
        } finally {
            logSign(data, privateKey, result);
        }
        return result;
    }
    
    @Override
    public boolean verify(String data, String publicKey, String sign) {
        Boolean result = null;
        try {
            result = scheme.verify(data, publicKey, sign);
        } finally {
            logVerify(data, publicKey, sign, result);
        }
        return result;
    }
    
    @Override
    public String string() {
        return scheme.string();
    }
    
    @Override
    public SignScheme useLogger() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * ==========================================
     * 签名算法: RSA2
     * 操作: [签名｜验签]
     * 密钥: !@#$%^&*()
     * 待验证签名: !@#$%^&*()
     * 结果: [String|Boolean]
     * ----------------「签名数据」------------------
     * {}
     * ==========================================
     */
    private void debug(String data, String key, String reSign, Object result, boolean isVerify) {
        final String optStr = isVerify ? "验签" : "签名";
        final StringJoiner joiner = new StringJoiner(System.lineSeparator())
                .add("")
                .add("========================================================================================")
                .add("签名算法: " + this.string())
                .add("操作: " + optStr)
                .add("->「密钥」:")
                .add(TextUtils.pretty(key));
        if (isVerify) {
            joiner.add("->「待验证签名」:").add(TextUtils.pretty(reSign));
        }
        joiner.add("->「结果」:")
                .add(TextUtils.pretty(result))
                .add("->「签名数据」:")
                .add(TextUtils.pretty(LangUtils.getOrDefault(data, "")))
                .add("========================================================================================");
        log.debug("{}", joiner);
    }
    
    private void logSign(String data, String key, Object result) {
        debug(data, key, null, result, false);
    }
    
    private void logVerify(String data, String key, String reSign, Object result) {
        debug(data, key, reSign, result, true);
    }
}
