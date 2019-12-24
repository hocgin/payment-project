package in.hocg.payment.wxpay.v1.request;

import in.hocg.payment.ConfigStorages;
import in.hocg.payment.PaymentServices;
import in.hocg.payment.wxpay.v1.WxPayConfigStorage;
import in.hocg.payment.wxpay.v1.WxPayService;
import in.hocg.payment.wxpay.v1.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class WxPayServiceTests {
    static WxPayConfigStorage configStorage;
    static WxPayService paymentService;
    
    @BeforeAll
    static void before() {
        configStorage = ConfigStorages.createConfigStorage(WxPayConfigStorage.class);
        configStorage.setAppId("****");
        configStorage.setKey("****");
        configStorage.setMchId("****");
        configStorage.setIsDev(false);
        paymentService = PaymentServices.createPaymentService(WxPayService.class, configStorage);
    }
    
    /**
     * 关闭订单
     */
    @Test
    void testCloseOrder() {
        final CloseOrderRequest request = new CloseOrderRequest();
        request.setOutTradeNo("");
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
     * 查询订单
     */
    @Test
    void testOrderQuery() {
        final OrderQueryRequest request = new OrderQueryRequest();
        request.setOutTradeNo("");
        final OrderQueryResponse response = paymentService.request(request);
        System.out.println(response);
    }
    
    /**
     * 交易保障
     */
    @Test
    void testPayitilReportRequest() {
        final PayitilReportRequest request = new PayitilReportRequest();
        request.setInterfaceUrl("");
        request.setExecuteTime("");
        request.setUserIp("");
        final PayitilReportResponse response = paymentService.request(request);
        System.out.println(response);
    }
    
    /**
     * 申请退款
     */
    @Test
    void testPayRefundRequest() {
        final PayRefundRequest request = new PayRefundRequest();
        request.setOutTradeNo("");
        request.setOutRefundNo("");
        request.setTotalFee("");
        request.setRefundFee("");
        final PayRefundResponse response = paymentService.request(request);
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
        final String orderNo = String.valueOf(System.currentTimeMillis());
        
        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setBody("body");
        request.setOutTradeNo(String.format("%s", orderNo));
        request.setTotalFee("1");
        request.setSpbillCreateIp("127.0.0.1");
        request.setNotifyUrl("http://www.baidu.com");
        request.setTradeType("JSAPI");
        request.setOpenId("openid");
        UnifiedOrderResponse response = paymentService.request(request);
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
        request.setTotalFee("1");
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
        
        BatchQueryCommentRequest request = new BatchQueryCommentRequest();
        request.setBeginTime("");
        request.setEndTime("");
        request.setOffset("1");
        final BatchQueryCommentResponse response = paymentService.request(request);
        System.out.println(response);
    }
    
}