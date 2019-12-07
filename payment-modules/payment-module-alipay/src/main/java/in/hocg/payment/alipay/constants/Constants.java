package in.hocg.payment.alipay.constants;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class Constants {
    /**
     * 支付宝 - 接口 - 服务器 - 正式环境
     */
    public static final String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";
    
    /**
     * 支付宝 - 接口 - 服务器 - 开发环境
     */
    public static final String ALIPAY_DEV_URL = "https://openapi.alipaydev.com/gateway.do";
    
    /**
     * 支付宝 - 接口 - 时间格式
     */
    public static final DateTimeFormatter ALIPAY_API_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 响应为成功时使用的 code
     */
    public static final String RESPONSE_SUCCESS_CODE = "10000";
}
