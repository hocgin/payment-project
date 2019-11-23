package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.SortedMap;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 * <p>
 * - 剔除特定参数(指定/值为空)
 * - 按参数字母升序排序
 * - 使用"Key=Value&Key=Value"形式进行拼接
 * - 进行签名
 *
 * @author hocgin
 */
@UtilityClass
public class SignUtils {
    
    
    /**
     * 对待签名字符串进行拼接
     *
     * @param params
     * @return
     */
    public static String getSignString(SortedMap<String, String> params) {
        return params.keySet().parallelStream()
                .map(fieldName -> String.format("%s=%s", fieldName, params.get(fieldName)))
                .reduce((s1, s2) -> s1 + "&" + s2).orElse("");
    }
    
    /**
     * 获取 KeyPair
     *
     * @param keySize
     * @return
     */
    public static KeyPair getKeyPair(int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(keySize);
            return generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
