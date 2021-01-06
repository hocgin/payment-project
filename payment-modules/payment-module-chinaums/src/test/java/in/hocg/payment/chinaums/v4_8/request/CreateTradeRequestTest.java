package in.hocg.payment.chinaums.v4_8.request;

import cn.hutool.core.util.RandomUtil;
import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.chinaums.v4_8.ChinaUmsConfigStorage;
import in.hocg.payment.chinaums.v4_8.ChinaUmsPayService;
import in.hocg.payment.chinaums.v4_8.message.TradeResultMessage;
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

    @Test
    void message() {
        String body = "buyerUsername=578***%40qq.com&payTime=2020-12-30+18%3A20%3A20&buyerCashPayAmt=1&connectSys=ALIPAY&sign=0AB592120BD71A1A7053C487190EAFFBF6CF18341D6618FD792EA8343B4896AC&merName=%E5%85%A8%E6%B8%A0%E9%81%93&mid=898310148160568&invoiceAmount=1&settleDate=2020-12-30&acqSpId=KFTEST12&billFunds=%E8%8A%B1%E5%91%97%3A1&buyerId=2088702841199405&tid=88880001&instMid=H5DEFAULT&receiptAmount=1&couponAmount=0&targetOrderId=2020123022001499401449542662&cardAttr=BALANCE&signType=SHA256&billFundsDesc=%E8%8A%B1%E5%91%97%E6%94%AF%E4%BB%980.01%E5%85%83%E3%80%82&orderDesc=xxx%E6%A0%A1%E5%8C%BA&seqId=00868904159N&merOrderId=3194yhdg0cdvw8qy909u&targetSys=Alipay+2.0&totalAmount=1&vg=iqwd&createTime=2020-12-30+18%3A20%3A13&buyerPayAmount=1&notifyId=9de16b80-5b5d-4d35-9b2d-7adfd4d110cf&subInst=101300&status=TRADE_SUCCESS";
        TradeResultMessage message = paymentService.message(body, TradeResultMessage.class);
        log.debug("==> {}", message);
    }
}
