package in.hocg.payment.sign.strategy.merge;

import in.hocg.payment.utils.SignUtils;

import java.util.Map;

/**
 * Created by hocgin on 2019/11/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DefaultMergeStrategy implements MergeStrategy {
    
    @Override
    public String apply(Map<String, Object> data) {
        return SignUtils.getSignString(data);
    }
}
