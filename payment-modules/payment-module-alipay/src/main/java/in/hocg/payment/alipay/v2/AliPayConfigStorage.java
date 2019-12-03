package in.hocg.payment.alipay.v2;

import in.hocg.payment.alipay.constants.Constants;
import in.hocg.payment.core.ConfigStorage;
import in.hocg.payment.sign.strategy.SignType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AliPayConfigStorage implements ConfigStorage {
    
    /**
     * 应用ID
     */
    @NonNull
    private String appId;
    
    /**
     * 应用私钥
     */
    @NonNull
    private String privateKey;
    
    /**
     * 支付宝公钥
     */
    @NonNull
    private String aliPayPublicKey;
    
    /**
     * 签名类型
     */
    @NonNull
    private SignType signType = SignType.RSA2;
    
    /**
     * 编码
     */
    @NonNull
    private String charset = "utf-8";
    
    @NonNull
    private String format = "json";
    
    @NonNull
    private String version = "1.0";
    
    /**
     * 是否沙箱环境
     */
    @NonNull
    private Boolean isDev = false;
    
    public String getUrl() {
        if (isDev) {
            return Constants.ALIPAY_DEV_URL;
        }
        return Constants.ALIPAY_URL;
    }
    
}
