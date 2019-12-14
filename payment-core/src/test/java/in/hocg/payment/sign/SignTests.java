package in.hocg.payment.sign;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.payment.sign.data.WaitSignObject;
import in.hocg.payment.utils.SignUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2019/11/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class SignTests {
    
    @Test
    void getSignString() {
        KeyPair keyPair = SignUtils.getKeyPair(1024);
        Base64.Encoder encoder = Base64.getEncoder();
        String privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
        
        WaitSignObject object = new WaitSignObject("superVal", "keyValue", 12, null);
        Map<String, Object> values = SignObjects.getSignValues(this);
    }
    
    @Test
    void testSignStrategy() {
        // 数据准备
        KeyPair keyPair = SignUtils.getKeyPair(1024);
        Base64.Encoder encoder = Base64.getEncoder();
        String privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
        HashMap<String, Object> values = Maps.newHashMap();
        HashMap<Object, Object> node = Maps.newHashMap();
        node.put("sd", "x");
        node.put("sx", 12);
        
        values.put("v1", 1);
        values.put("v2", 34f);
        values.put("v3", 34d);
        values.put("v4", LocalDateTime.now());
        values.put("v5", "sgdka");
        values.put("v6", Lists.newArrayList("1", "2"));
        values.put("v7", Lists.newArrayList(node, node));
        
        // 签名
        SignType rsa = SignType.RSA2;
        SignValue signValue = new SignValue().handle(values);
        String data = signValue.getSignValue();
        
        
        String signString = rsa.sign(data, privateKey);
        boolean verify = rsa.verify(data, publicKey, signString);
        Assertions.assertTrue(verify);
    }
    
    @Test
    void testOk() {
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2X977LaTocs9QuSleAx1gibGvMhAFW3bBAf1wr3YP26ckOIqa+FIcET4tiBlCleq+Ci4sRBBdcKPtU0c/f6SlDPP9OqnqRXUEuXsiUMJ7OPrstoIddLCFqHHmaRpXINfZjmgGtKvruDf78bX2FnYa2uRGNhw3ckIOlF4A7NEIiJZl3fum1/qA6oEGTjn1QiB5//8EN3ERYCDBkhgS1g2T0kuL6Ank+0+SWuE0Fm08vU4uP4icb5LRdRHfoerfHcEIxhbVtihWHSmLgGibNEI7dxFiAz3Wotp8zsonwF9Q5cEbpYPpHVpx/i7RpF7yFUm5M61uzonQah7UNrV1Ww4JAgMBAAECggEAf8bJ9tgzCz2tbRReHGU4RvQSTvpXaTl9CZt4U2RL5q5x+5m12wASn2GhW8tYT2O0NXPyh8ckZCNQZy4K5D1tQMrDg+9/Lwl9BFNkJ1XH/QeeHw18OmEQcITlUJbhApybPu1cix44ug23A8mFQKbaFtS4TU0KFfrytz5SYnmJt1y0oOMN+dmYxnbh+w2Av/N09hj61a47WocOQDW8ytBoDD4nZseFUSQ+vSNK3ialvZQ7zXTvyLads6yONcbOCdk8u4+1UUnex3PX+ySNSjQ1JuTQot+FloAZyywOzXFeVkCtZuQpe/gj9ok8i7iuT/UK/5ouDbJJ9PTJxY14t6650QKBgQDZ8TsBzeYcnZsDQC74YVzEsZhT9MdwAOj9LlSy17g3IRT/zHAy4L3alzOjOT8eLYDw3G1oZLo7zniM1L3kePjE2hhnA0yiPBJcOJzslr40nWdTRc8E4ag3Q5Mv0oQXwU9udLnUNak5CXFsV+cx6VcdcGhHzggxY9+rdyrE7K8x3QKBgQDWOKMjbxhPdLt8zd54+Jy2kUstzW0FbO9FTGw3cwHyAj2I6icjIdMug1RKUi+gnQgu8jkVSVRfUqIlJVtCR4dyYomIJIeHk87wGMv84VKugKylCjYxjYgR0DCaXRWiCHQb+7esJ4WXt7Nww7dPfziZylRbB7WPZ+/jhPhHl0mIHQKBgEBqJhCQdJS8mFZLoBZVYH/aJbWawV9/RV2fVfVOAOp6YqSAHiFLf5Gd4us5PkiDFnsaC1QxgUGv8r1dG4rtnklAVLoNpZbFvn93VBoxK6KNaz6XgWpl77v1wwj9ZYFH51w0L8Bi49Mx0U4+ZNzBpLfUw12FrbI7XJ5nKELv2ZAZAoGBAIbRXDJnr3gJ8hjIg3PEmvP3GsY3m54ngaouP4jiE15YdJufKYRdvEdwlXK0qI6/ZTAOd0hjPvtCyRLxoK5kz+R4CTAqNTVpG3pVUMPUlrGF/6FafOLQvMrhKEVtwbiY82HNGDn7IYNrND4Knmokmd2HzXEAuA4Jjpq0y4BawQctAoGBAJZauDM0SONibPROwobeQZPdv5YW4EuQnMBoittmjsqjv2V5csw0K9FJVjmtcOURqKpBw+MJWuxEpV5MNiSqBmBmnyGk6ayRKVFVhkmj8OOzHhdGe+6xJa+L59DyHyqSY45Yu6DBLop37/VzWwqBitcKWRmyQzfF9BPObV1xR7lD";
        
        String result = "eBZ2PN9TUsRAA9jCLEd5Hiiii2FfYyL9jKjiyg84aG8ekD3tGNCRYnCKMSVWwOCmyWsGdCZYwmYbzZVVQgWyd3GKxl607QkuiPdp325ybk9LR0TtDWE8SLyqjD6aGgN1u0Xi/giNGYMbugT7sw9mD/lAqhPQ6RKcRDrA1PGYvZXtIG0j0LpFAWT/o4NaXY9b1S9TCP7xkoa48utIj5nkBhulg3gVCU1yFu5zUzD3HSU82LyzKVwI7Fcl0MrpC6YbzNtf+le/wPBrm0LSLb/F+51sGAM0Ax0p6FSVSRMoxFKCbsGgdebogPhPFxQNbvRIA5DCT/wz7JjV8uBuwtqp3g==";
        String content = "alipay_sdk=alipay-sdk-java-4.8.62.ALL&app_id=2016080300154586&biz_content={\"out_trade_no\":\"201911270908381158731\",\"total_amount\":\"88.88\",\"subject\":\"iPhone+Xs+Max+256G\",\"buyer_id\":\"2088102175953034\"}&charset=utf-8&format=json&method=alipay.trade.create&sign_type=RSA2&timestamp=2019-12-02 23:06:15&version=1.0";
        
        String sign = SignType.RSA2.sign(content, privateKey);
        Assertions.assertEquals(result, sign);
    }
    
}