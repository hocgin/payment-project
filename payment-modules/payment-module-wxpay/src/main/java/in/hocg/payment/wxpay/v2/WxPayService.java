package in.hocg.payment.wxpay.v2;

import in.hocg.payment.PaymentMessage;
import in.hocg.payment.PaymentService;
import in.hocg.payment.wxpay.convert.WxPayConverts;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.v2.request.GetSignKeyRequest;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public class WxPayService extends PaymentService<WxPayConfigStorage> {

    public WxPayService(WxPayConfigStorage configStorage) {
        super(configStorage);
        if (configStorage.getIsDev()){
            deployDevEnv();
        }
    }

    /**
     * 部署沙箱环境
     */
    private void deployDevEnv() {
        String key = getSandBoxSignKey();
        getConfigStorage().setKey(key);
    }

    /**
     * 获取沙箱key
     * @return 沙箱key
     */
    private String getSandBoxSignKey() {
        final GetSignKeyRequest request = new GetSignKeyRequest();
        request.setSignType(WxSignType.MD5.string());
        return request(request).getKey();
    }

    @Override
    public <T extends PaymentMessage> T message(String content, Class<T> clazz) {
        return message(content, WxPayConverts.MESSAGE, clazz);
    }
}
