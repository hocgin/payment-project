package in.hocg.payment.utils;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class UriUtilsTest {
    
    @Test
    void getUrl() {
        HashMap<String, Object> params = Maps.newHashMap();
        HashMap<String, Object> e = Maps.newHashMap();
        e.put("sd", "sd");
        params.put("a", "sd");
        params.put("c", 1);
        params.put("d", false);
        params.put("e", e);
    
        String url = HttpUtils.getUrl("https://www.baidu.com", params);
    
        Assertions.assertTrue(url.equals("https://www.baidu.com?a=sd&c=1&d=false&e={sd=sd}"));
    }
}