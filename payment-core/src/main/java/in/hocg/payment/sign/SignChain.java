package in.hocg.payment.sign;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SignChain extends Function<Map<String, Object>, Map<String, Object>> {
}
