package in.hocg.payment.wxpay.v1;

import in.hocg.payment.core.ConfigStorage;
import in.hocg.payment.wxpay.constants.Constants;
import in.hocg.payment.wxpay.sign.WxSignType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class WxPayConfigStorage implements ConfigStorage {
    
    /**
     * APPID
     */
    @NonNull
    private String appId;
    
    /**
     * 微信支付商户号
     */
    @NonNull
    private String mchId;
    
    /**
     * API密钥
     */
    @NonNull
    private String key;
    
    /**
     * 签名类型
     */
    @NonNull
    private WxSignType signType = WxSignType.MD5;
    
    /**
     * 是否沙箱环境
     */
    @NonNull
    private Boolean isDev = false;
    
    public String getUrl() {
        if (isDev) {
            return Constants.WXPAY_DEV_URL;
        }
        return Constants.WXPAY_URL;
    }
}
