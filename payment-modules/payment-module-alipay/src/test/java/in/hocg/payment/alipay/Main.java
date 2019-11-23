package in.hocg.payment.alipay;

import com.google.common.collect.Lists;
import in.hocg.payment.alipay.v2.request.AppPayRequest;
import in.hocg.payment.sign.SignHelper;
import in.hocg.payment.sign.SignType;
import in.hocg.payment.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class Main {
    
    @Test
    public void testObjectSign() {
        KeyPair keyPair = SignUtils.getKeyPair(1024);
        Base64.Encoder encoder = Base64.getEncoder();
        String privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
        
        AppPayRequest request = new AppPayRequest();
        request.setAuthCode("authcode");
        ArrayList<AppPayRequest.GoodsDetail> goodsDetail = Lists.newArrayList();
        goodsDetail.add(new AppPayRequest.GoodsDetail("sdd", "sd", "sd", "sd"));
        request.setGoodsDetail(goodsDetail);
        
        String signString = SignHelper.getSignString(request, privateKey, SignType.RSA);
        log.debug("SIGN {}", signString);
    }
}
