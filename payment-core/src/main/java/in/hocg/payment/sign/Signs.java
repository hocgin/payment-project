package in.hocg.payment.sign;

import com.google.common.collect.Maps;
import in.hocg.payment.sign.strategy.SignType;
import in.hocg.payment.sign.strategy.merge.MergeStrategy;
import in.hocg.payment.sign.strategy.value.ValueStrategy;
import lombok.NonNull;

import java.util.Map;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Signs {
    
    /**
     * 参数处理
     *
     * @param params
     * @param strategy
     * @return
     */
    public static Map<String, Object> getString(Map<String, Object> params,
                                                ValueStrategy strategy) {
        Map<String, Object> result = Maps.newTreeMap();
        Maps.newHashMap(params)
                .entrySet()
                .stream()
                .map(strategy::map)
                .filter(strategy::filter)
                .sorted(strategy.sorted())
                .map(strategy::mapOrdered)
                .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }
    
    /**
     * 合并为待签名字符串
     *
     * @param data
     * @param strategy
     * @return
     */
    public static String merge(Map<String, Object> data, MergeStrategy strategy) {
        return strategy.apply(data);
    }
    
    
    /**
     * 获取签名
     *
     * @param content
     * @param privateKey
     * @param signType
     * @return
     */
    public static String getSign(@NonNull String content,
                                 @NonNull String privateKey,
                                 @NonNull SignType signType) {
        return signType.sign(content, privateKey);
    }
    
    /**
     * 验证签名
     *
     * @param content
     * @param publicKey
     * @param signType
     * @param signValue
     * @return
     */
    public static boolean verifySign(@NonNull String content,
                                     @NonNull String publicKey,
                                     @NonNull SignType signType,
                                     @NonNull String signValue) {
        return signType.verify(content, publicKey, signValue);
    }
}
