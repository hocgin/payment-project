package in.hocg.payment.wxpay.v2;

import in.hocg.payment.PaymentMessage;
import in.hocg.payment.PaymentService;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.convert.WxPayConverts;
import in.hocg.payment.wxpay.sign.WxSignType;
import in.hocg.payment.wxpay.v2.request.GetSignKeyRequest;
import in.hocg.payment.wxpay.v2.response.GetSignKeyResponse;
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
        Helpers.initCertHttpClient(configStorage);
        if (configStorage.getIsDev()){
            deployDevEnv();
        }
    }

    /**
     * 部署沙箱环境
     */
    private void deployDevEnv() {
        WxPayConfigStorage configStorage = getConfigStorage();
        configStorage.setSignType(WxSignType.MD5);

        String key = getSandBoxSignKey();
        configStorage.setKey(key);
    }

    /**
     * 获取沙箱key
     * @return 沙箱key
     */
    private String getSandBoxSignKey() {
        GetSignKeyRequest request = new GetSignKeyRequest();
        GetSignKeyResponse response = request(request);
        return response.getKey();
    }

    @Override
    public <T extends PaymentMessage> T message(String content, Class<T> clazz) {
        return message(content, WxPayConverts.MESSAGE, clazz);
    }
}
