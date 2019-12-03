package in.hocg.payment.sign;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.payment.sign.strategy.SignType;
import in.hocg.payment.utils.SignUtils;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class SignHelper {
    /**
     * 值处理
     */
    private final List<Strategy> chains = Lists.newArrayList();
    
    /**
     * 合并策略
     */
    private Merge merge = SignUtils::getSignString;
    
    /**
     * 添加处理节点
     *
     * @param handle
     * @return
     */
    public SignHelper add(Strategy handle) {
        chains.add(handle);
        return this;
    }
    
    /**
     * 设置合并策略
     *
     * @param merge
     * @return
     */
    public SignHelper setMerge(Merge merge) {
        this.merge = merge;
        return this;
    }
    
    /**
     * 获取处理后的值
     *
     * @param values
     * @return
     */
    public Map<String, Object> getSignValue(Map<String, Object> values) {
        Map<String, Object> result = Maps.newHashMap(values);
        for (Strategy chain : chains) {
            result = chain.apply(result);
        }
        return result;
    }
    
    /**
     * 触发合并策略
     *
     * @param values
     * @return
     */
    public String merge(Map<String, Object> values) {
        return this.merge.apply(values);
    }
    
    /**
     * 1. 处理值
     * 2. 合并成URL
     *
     * @param values
     * @return
     */
    public String getSignString(Map<String, Object> values) {
        return this.merge(getSignValue(values));
    }
    
    
    /**
     * 获取签名
     *
     * @param content
     * @param privateKey
     * @param signType
     * @return
     */
    public String getSign(@NonNull String content,
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
    public boolean verifySign(@NonNull String content,
                              @NonNull String publicKey,
                              @NonNull SignType signType,
                              @NonNull String signValue) {
        return signType.verify(content, publicKey, signValue);
    }
    
    public interface Strategy extends Function<Map<String, Object>, Map<String, Object>> {
    }
    
    public interface Merge extends Function<Map<String, Object>, String> {
    }
}
