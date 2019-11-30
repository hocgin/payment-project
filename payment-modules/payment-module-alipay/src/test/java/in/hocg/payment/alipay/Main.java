package in.hocg.payment.alipay;

import com.google.common.collect.Lists;
import in.hocg.payment.alipay.v2.request.TradePayRequest;
import in.hocg.payment.alipay.v2.request.inner.GoodsDetail;
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
        
        TradePayRequest request = new TradePayRequest();
        request.setAuthCode("authcode");
        ArrayList<GoodsDetail> goodsDetail = Lists.newArrayList();
        goodsDetail.add(new GoodsDetail("sdd", "sd", "sd", "sd"));
        request.setGoodsDetail(goodsDetail);
        
    
    }
}
