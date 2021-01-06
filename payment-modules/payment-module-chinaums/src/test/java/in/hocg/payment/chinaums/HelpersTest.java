package in.hocg.payment.chinaums;

import in.hocg.payment.sign.SignType;
import in.hocg.payment.sign.SignValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2021/1/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class HelpersTest {

    @Test
    void newSignValue() {
        Map<String, Object> params = new HashMap<>();
        params.put("msgSrc", "WWW.TEST.COM");
        params.put("requestTimestamp", "2017-06-26 17:28:02");
        params.put("msgType", "bills.getQRCode");
        params.put("mid", "898340149000005");
        params.put("tid", "88880001");
        params.put("totalAmount", "1");
        params.put("walletOption", "SINGLE");
        params.put("instMid", "QRPAYDEFAULT");
        params.put("billDate", "2017-06-26");
        params.put("billNo", "31940000201700002");
        params.put("goods", "[{\"body\":\"微信二维码测试\",\"price\":\"1\",\"goodsName\":\"微信二维码测试\",\"goodsId\":\"1\",\"quantity\":\"1\",\"goodsCategory\":\"TEST\"}]");

        SignValue signValueHelper = Helpers.newSignValue().handle(params);

        String signValue = signValueHelper.getSignValue();

        String newSignStr = SignType.SHA256.sign(signValue, "fcAmtnx7MwismjWNhNKdHC44mNXtnEQeJkRrhKJwyrW2ysRR");
        String okSignStr = "A9ECED8DD8425D1FC4047CF94E672C69ED1073557EE831C51287341CFAB0B21F";
        Assertions.assertEquals(okSignStr, newSignStr);
    }
}
