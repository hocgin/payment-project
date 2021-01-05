package in.hocg.payment.chinaums.v4_8;

import in.hocg.payment.ConfigStorage;
import in.hocg.payment.chinaums.constants.Constants;
import in.hocg.payment.sign.SignType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ChinaUmsConfigStorage implements ConfigStorage {
    /**
     * 商户号
     */
    @NonNull
    private String mid;
    /**
     * 终端
     */
    @NonNull
    private String tid;
    /**
     * 消息来源
     */
    @NonNull
    private String msgSrc;
    /**
     * 消息ID
     * - 订单编号前缀
     */
    @NonNull
    private String msgSrcId;
    /**
     * 通信密钥
     */
    @NonNull
    private String txmKey;
    /**
     * 是否沙箱环境
     */
    @NonNull
    private Boolean isDev = false;
    /**
     * 签名方式
     */
    @NonNull
    private SignType signType = SignType.SHA256;


    public String getUrl() {
        if (isDev) {
            return Constants.DEV_URL;
        }
        return Constants.PROD_URL;
    }
}
