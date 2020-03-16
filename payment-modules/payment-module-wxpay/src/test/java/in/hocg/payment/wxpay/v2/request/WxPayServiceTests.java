package in.hocg.payment.wxpay.v2.request;

import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class WxPayServiceTests {
    static WxPayConfigStorage configStorage;
    static WxPayService paymentService;
    static String orderNo;
    static String refundOrderNo;

    @BeforeAll
    static void before() {
        configStorage = ConfigStorages.createConfigStorage(WxPayConfigStorage.class);
        configStorage.setAppId(System.getenv("wx_pay_app_id"));
        configStorage.setKey(System.getenv("wx_pay_key"));
        configStorage.setMchId(System.getenv("wx_mch_id"));
        configStorage.setCertFile(new File(System.getenv("wx_cert_file")));
        configStorage.setIsDev(true);
        paymentService = PaymentServices.createPaymentService(WxPayService.class, configStorage);
        orderNo = String.valueOf(System.currentTimeMillis());
        refundOrderNo = String.valueOf(System.currentTimeMillis()+2);
    }

    /**
     * 关闭订单
     */
    @Test
    void testCloseOrder() {
        final CloseOrderRequest request = new CloseOrderRequest();
        request.setOutTradeNo(orderNo);
        CloseOrderResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 下载对账单
     */
    @Test
    void testDownloadBill() {
        final DownloadBillRequest request = new DownloadBillRequest();
        request.setBillType("ALL");
        request.setBillDate("20191201");
        DownloadBillResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 下载资金账单
     */
    @Test
    void testDownloadFundFlow() {
        final DownloadFundFlowRequest request = new DownloadFundFlowRequest();
        request.setAccountType("Basic");
        request.setBillDate("20191201");
        final DownloadFundFlowResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 交易保障
     */
    @Test
    void testPayitilReportRequest() {
        final PayitilReportRequest request = new PayitilReportRequest();
        request.setInterfaceUrl("https://api.mch.weixin.qq.com/pay/unifiedorder");
        request.setExecuteTime("1000");
        request.setUserIp("8.8.8.8");
        final PayitilReportResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 查询退款
     */
    @Test
    void testRefundQuery() {
        final RefundQueryRequest request = new RefundQueryRequest();
        request.setOutTradeNo("");
        final RefundQueryResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 统一下单 - JSAPI
     */
    @Test
    void testUnifiedOrderRequest_jsapi() {
        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setBody("body");
        request.setOutTradeNo(String.format("%s", orderNo));
        request.setTotalFee("101");
        request.setSpbillCreateIp("127.0.0.1");
        request.setNotifyUrl("http://www.baidu.com");
        request.setTradeType("JSAPI");
        request.setOpenId("opQx55EOxwwO8kyQKrQePlHTOBAg");
        UnifiedOrderResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 查询订单
     */
    @Test
    void testOrderQuery() {
        final OrderQueryRequest request = new OrderQueryRequest();
        request.setOutTradeNo(orderNo);
        final OrderQueryResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 申请退款
     */
    @Test
    void testPayRefundRequest() {
        final PayRefundRequest request = new PayRefundRequest();
        request.setOutTradeNo(orderNo);
        request.setOutRefundNo(refundOrderNo);
        request.setTotalFee("101");
        request.setRefundFee("101");
        final PayRefundResponse response = paymentService.request(request);
        System.out.println(response);
    }
    /**
     * 统一下单 - APP
     */
    @Test
    void testUnifiedOrderRequest_app() {
        final String orderNo = String.valueOf(System.currentTimeMillis());

        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setBody("body");
        request.setOutTradeNo(String.format("%s", orderNo));
        request.setTotalFee("101");
        request.setSpbillCreateIp("127.0.0.1");
        request.setNotifyUrl("http://www.baidu.com");
        request.setTradeType("APP");
        UnifiedOrderResponse response = paymentService.request(request);
        System.out.println(response);
    }

    /**
     * 拉取订单评价数据
     */
    @Test
    void testBatchQueryCommentRequest() {
//
//        BatchQueryCommentRequest request = new BatchQueryCommentRequest();
//        request.setBeginTime("");
//        request.setEndTime("");
//        request.setOffset("1");
//        final BatchQueryCommentResponse response = paymentService.request(request);
//        System.out.println(response);
    }

}
