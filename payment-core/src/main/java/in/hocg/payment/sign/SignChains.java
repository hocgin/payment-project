package in.hocg.payment.sign;

import com.google.common.collect.Lists;
import in.hocg.payment.utils.SignUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class SignChains {
    public static final Function<Map<String, Object>, String> DEFAULT_MERGE = SignUtils::getSignString;
    
    private List<SignChain> chains = Lists.newArrayList();
    
    /**
     * 执行签名链
     *
     * @param object
     * @param mergeFunc
     * @return
     */
    public String handle(Object object, Function<Map<String, Object>, String> mergeFunc) {
        Map<String, Object> values = SignHelper.getSignValues(object);
        for (SignChain chain : chains) {
            values = chain.apply(values);
        }
        return mergeFunc.apply(values);
    }
    
    /**
     * 添加处理链
     *
     * @param chain
     * @return
     */
    public SignChains addChain(SignChain chain) {
        chains.add(chain);
        return this;
    }
}
