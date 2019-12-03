package in.hocg.payment.sign.strategy.value;

import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2019/11/30.
 * email: hocgin@gmail.com
 * 默认签名策略
 * - 获取签名字段
 * - 过滤值为 NULL 字段
 * - 对值进行 URLEncode 编码
 * - 进行自然排序
 *
 * @author hocgin
 */
public class DefaultValueStrategy implements ValueStrategy {
    
    @Override
    public boolean filter(Map.Entry<String, Object> entry) {
        Object value = entry.getValue();
        return Objects.nonNull(value);
    }
    
    @Override
    public Map.Entry<String, Object> mapOrdered(Map.Entry<String, Object> entry) {
        Object value = entry.getValue();
//        entry.setValue(URLEncoder.encode(String.valueOf(value)));
        return entry;
    }
}
