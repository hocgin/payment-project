package in.hocg.payment.chinaums.v4_8.request;

import cn.hutool.core.util.RandomUtil;
import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.chinaums.v4_8.ChinaUmsConfigStorage;
import in.hocg.payment.chinaums.v4_8.ChinaUmsPayService;
import in.hocg.payment.chinaums.v4_8.response.CloseTradeResponse;
import in.hocg.payment.chinaums.v4_8.response.CreateTradeResponse;
import in.hocg.payment.chinaums.v4_8.response.QueryTradeResponse;
import in.hocg.payment.chinaums.v4_8.response.RefundTradeResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class CreateTradeRequestTest {
    static ChinaUmsConfigStorage configStorage;
    static ChinaUmsPayService paymentService;

    @BeforeAll
    static void before() {
        configStorage = ConfigStorages.createConfigStorage(ChinaUmsConfigStorage.class)
            .setTxmKey("fcAmtnx7MwismjWNhNKdHC44mNXtnEQeJkRrhKJwyrW2ysRR")
            .setMid("898310148160568")
            .setTid("88880001")
            .setMsgSrc("WWW.TEST.COM")
            .setMsgSrcId("3194")
            .setIsDev(true);
        paymentService = PaymentServices.createPaymentService(ChinaUmsPayService.class, configStorage);
    }

    @Test
    void h5Pay() {
        CreateTradeRequest request = new CreateTradeRequest();
        request.setOrderSn(RandomUtil.randomString(12));
        request.setMsgType("trade.h5Pay");
        request.setTotalAmount(BigDecimal.ONE);
        CreateTradeResponse response = paymentService.request(request);
        log.debug("==> {}", response);
    }

    @Test
    void jsPay() {
        CreateTradeRequest request = new CreateTradeRequest();
        request.setOrderSn(RandomUtil.randomString(12));
        request.setMsgType("WXPay.jsPay");
        request.setTotalAmount(BigDecimal.ONE);
        CreateTradeResponse response = paymentService.request(request);
        log.debug("==> {}", response);
    }

    @Test
    void query() {
        QueryTradeRequest request = new QueryTradeRequest();
        request.setOrderSn("deo5yhzrb1s53whlg1x2xx");
        QueryTradeResponse response = paymentService.request(request);
        log.debug("==> {}", response);
    }

    @Test
    void close() {
        CloseTradeRequest request = new CloseTradeRequest();
        request.setOrderSn("deo5yhzrb1s53whlg1x2xx");
        CloseTradeResponse response = paymentService.request(request);
        log.debug("==> {}", response);
    }

    @Test
    void refund() {
        RefundTradeRequest request = new RefundTradeRequest();
        request.setOrderSn("deo5yhzrb1s53whlg1x2xx");
        request.setRefundAmount(BigDecimal.ONE);
        RefundTradeResponse response = paymentService.request(request);
        log.debug("==> {}", response);
    }
}
