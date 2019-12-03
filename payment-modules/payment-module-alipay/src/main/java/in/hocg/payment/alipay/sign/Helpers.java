package in.hocg.payment.alipay.sign;

import com.google.common.collect.Maps;
import in.hocg.payment.sign.SignHelper;
import in.hocg.payment.utils.SignUtils;

import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Helpers {
    
    /**
     * 支付宝签名策略
     */
    public static SignHelper AliPay = new SignHelper()
            // 过滤空值
            .add(new SignHelper.Strategy() {
                @Override
                public Map<String, Object> apply(Map<String, Object> map) {
                    Map<String, Object> result = Maps.newHashMap();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        Object value = entry.getValue();
                        if (Objects.isNull(value)) {
                            continue;
                        }
                        result.put(entry.getKey(), value);
                    }
                    return result;
                }
            })
            // 根据 KEY 排序
            .add(new SignHelper.Strategy() {
                @Override
                public Map<String, Object> apply(Map<String, Object> map) {
                    Map<String, Object> result = Maps.newTreeMap();
                    Maps.newHashMap(map)
                            .entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey())
                            .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
                    return result;
                }
            })
            // 合并策略
            .setMerge(SignUtils::getSignString);
    
}
