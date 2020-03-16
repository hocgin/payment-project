package in.hocg.payment.wxpay.v2;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.wxpay.constants.Constants;
import in.hocg.payment.wxpay.sign.WxSignType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.io.File;

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
     * [必选] APP ID
     */
    @NonNull
    private String appId;

    /**
     * [必选] 微信支付商户号
     */
    @NonNull
    private String mchId;

    /**
     * [必选] API密钥
     */
    @NonNull
    private String key;

    /**
     * [必选] 签名类型
     */
    @NonNull
    private WxSignType signType = WxSignType.HMAC_SHA256;

    /**
     * [可选] 证书
     */
    private File certFile;

    /**
     * [可选] 是否沙箱环境
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
