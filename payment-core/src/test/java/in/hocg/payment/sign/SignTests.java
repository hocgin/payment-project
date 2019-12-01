package in.hocg.payment.sign;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.payment.sign.data.WaitSignObject;
import in.hocg.payment.sign.strategy.SignType;
import in.hocg.payment.sign.strategy.merge.DefaultMergeStrategy;
import in.hocg.payment.sign.strategy.value.DefaultValueStrategy;
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
        SignType rsa = SignType.RSA;
        DefaultValueStrategy strategy = new DefaultValueStrategy();
        Map<String, Object> data = Signs.getString(values, strategy);
        DefaultMergeStrategy mergeStrategy = new DefaultMergeStrategy();
        String params = Signs.merge(data, mergeStrategy);
        String signString = Signs.getSign(params, privateKey, rsa);
        boolean result = Signs.verifySign(params, publicKey, rsa, signString);
        Assertions.assertTrue(result);
    }
    
}