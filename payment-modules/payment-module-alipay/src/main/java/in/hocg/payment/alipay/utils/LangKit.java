package in.hocg.payment.alipay.utils;

import in.hocg.payment.net.HttpClient;
import in.hocg.payment.net.OkHttpClient;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class LangKit {
    
    
    public static HttpClient getHttpClient() {
        return HttpClient.getInstance(OkHttpClient.class);
    }
}
