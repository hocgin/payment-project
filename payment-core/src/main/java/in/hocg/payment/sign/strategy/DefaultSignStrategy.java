package in.hocg.payment.sign.strategy;

import in.hocg.payment.sign.SignChains;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.utils.MapUtils;
import lombok.NonNull;

import java.util.HashMap;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 * <p>
 * 默认签名策略
 * - 获取签名字段
 * - 过滤值为 NULL 字段
 * - 进行自然排序
 *
 * @author hocgin
 */
public class DefaultSignStrategy {
    
    public static final SignChains CHAINS = new SignChains()
            // 过滤 value 为 NULL
            .addChain(data -> {
                HashMap<String, Object> map = new HashMap<>();
                for (String key : data.keySet()) {
                    Object value = data.get(key);
                    if (value != null) {
                        map.put(key, value);
                    }
                }
                return map;
            })
            // 进行自然排序
            .addChain(data -> MapUtils.sortedUseKey(data, true));
    
    /**
     * 根据对象获取签名字符串
     *
     * @param object
     * @return
     */
    public static String getSignString(@NonNull Object object,
                                       @NonNull String privateKey,
                                       @NonNull SignType signType) {
        String waitSignString = CHAINS.handle(object, SignChains.DEFAULT_MERGE);
        return signType.sign(waitSignString, privateKey);
    }
    
    /**
     * 验证签名
     *
     * @param object
     * @param publicKey
     * @param signType
     * @param signValue
     * @return
     */
    public static boolean verifySign(@NonNull Object object,
                                     @NonNull String publicKey,
                                     @NonNull SignType signType,
                                     @NonNull String signValue) {
        String waitSignString = CHAINS.handle(object, SignChains.DEFAULT_MERGE);
        return signType.verify(waitSignString, publicKey, signValue);
    }
}
