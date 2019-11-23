package in.hocg.payment.alipay.v2;

import in.hocg.payment.core.ConfigStorage;
import in.hocg.payment.sign.SignType;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Data
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
     * 同步通知URL
     */
    @NonNull
    private String syncNotifyUrl;
    
    /**
     * 异步通知URL
     */
    @NonNull
    private String asyncNotifyUrl;
    
    /**
     * 签名类型
     */
    @NonNull
    private SignType signType;
    
    /**
     * 编码
     */
    @NonNull
    private String charset = "UTF-8";
    
}
