package in.hocg.payment.sign.strategy.merge;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by hocgin on 2019/11/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface MergeStrategy extends Function<Map<String, Object>, String> {

}
