package in.hocg.payment.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Base64;

/**
 * Created by hocgin on 2019/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class AES256EncryptTest {

    @Test
    void aes256() {
        String content = "123";
        final String key = MD5Encrypt.encode32("hocgin");
        log.debug("原文: {}", content);
        log.debug("KEY: {}", key);

        byte[] encode = AES256Encrypt.encode(content, key);
        final String resultString = Base64.getEncoder().encodeToString(encode);
        log.debug("加密后: {}", resultString);

        final String decode = AES256Encrypt.decode(encode, key);
        log.debug("解密后: {}", decode);
        Assertions.assertEquals(content, decode);
    }


    @Test
    void aes2562() {
        String content = "<root>\n" +
                "<out_refund_no><![CDATA[131811191610442717309]]></out_refund_no>\n" +
                "<out_trade_no><![CDATA[71106718111915575302817]]></out_trade_no>\n" +
                "<refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>\n" +
                "<refund_fee><![CDATA[3960]]></refund_fee>\n" +
                "<refund_id><![CDATA[50000408942018111907145868882]]></refund_id>\n" +
                "<refund_recv_accout><![CDATA[支付用户零钱]]></refund_recv_accout>\n" +
                "<refund_request_source><![CDATA[API]]></refund_request_source>\n" +
                "<refund_status><![CDATA[SUCCESS]]></refund_status>\n" +
                "<settlement_refund_fee><![CDATA[3960]]></settlement_refund_fee>\n" +
                "<settlement_total_fee><![CDATA[3960]]></settlement_total_fee>\n" +
                "<success_time><![CDATA[2018-11-19 16:24:13]]></success_time>\n" +
                "<total_fee><![CDATA[3960]]></total_fee>\n" +
                "<transaction_id><![CDATA[4200000215201811190261405420]]></transaction_id>\n" +
                "</root>";
        final String key = MD5Encrypt.encode32("key");
        log.debug("原文: {}", content);
        log.debug("KEY: {}", key);

        byte[] encode = AES256Encrypt.encode(content, key);
        final String resultString = Base64.getEncoder().encodeToString(encode);
        log.debug("加密后: {}", resultString);
    }

}
