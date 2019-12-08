package in.hocg.payment.wxpay.constants;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2019/12/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class Constants {
    
    public static final String WXPAY_DEV_URL = "https://api.mch.weixin.qq.com/sandboxnew";
    public static final String WXPAY_URL = "https://api.mch.weixin.qq.com";
    /**
     * 响应为成功时使用的 code
     */
    public static final String RESPONSE_SUCCESS_CODE = "SUCCESS";
}
