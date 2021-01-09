package in.hocg.payment.complete.v1.feature.wxpay;

import in.hocg.payment.complete.core.TradeInfo;
import in.hocg.payment.complete.core.TradeQueryResult;
import in.hocg.payment.complete.v1.feature.PaymentFeature;
import in.hocg.payment.wxpay.v2.request.CloseOrderRequest;
import in.hocg.payment.wxpay.v2.request.OrderQueryRequest;
import in.hocg.payment.wxpay.v2.request.UnifiedOrderRequest;
import in.hocg.payment.wxpay.v2.response.OrderQueryResponse;
import in.hocg.payment.wxpay.v2.response.UnifiedOrderResponse;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface WxPayPaymentFeature extends PaymentFeature {

    default String tradeJsapiPay(TradeInfo ro) {
        String title = ro.getTitle();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal totalAmount = ro.getTotalAmount();
        String outTradeSn = ro.getOutTradeSn();
        String buyerIp = ro.getBuyerIp();
        String openid = ro.getOpenid();

        UnifiedOrderRequest request = WxPayHelper.buildTradeRequest("JSAPI", title, outTradeSn, notifyUrl, totalAmount, buyerIp, openid);
        UnifiedOrderResponse response = getPaymentService().request(request);
        return response.getContent();
    }

    default String tradeAppPay(TradeInfo ro) {
        String title = ro.getTitle();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal totalAmount = ro.getTotalAmount();
        String outTradeSn = ro.getOutTradeSn();
        String buyerIp = ro.getBuyerIp();
        String openid = ro.getOpenid();

        UnifiedOrderRequest request = WxPayHelper.buildTradeRequest("APP", title, outTradeSn, notifyUrl, totalAmount, buyerIp, openid);
        UnifiedOrderResponse response = getPaymentService().request(request);
        return response.getContent();
    }

    default String tradeNativePay(TradeInfo ro) {
        String title = ro.getTitle();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal totalAmount = ro.getTotalAmount();
        String outTradeSn = ro.getOutTradeSn();
        String buyerIp = ro.getBuyerIp();
        String openid = ro.getOpenid();

        UnifiedOrderRequest request = WxPayHelper.buildTradeRequest("NATIVE", title, outTradeSn, notifyUrl, totalAmount, buyerIp, openid);
        UnifiedOrderResponse response = getPaymentService().request(request);
        return response.getContent();
    }

    default TradeQueryResult tradeQuery(String outTradeSn) {
        OrderQueryRequest request = new OrderQueryRequest();
        request.setOutTradeNo(outTradeSn);

        OrderQueryResponse response = getPaymentService().request(request);
        BigDecimal totalAmount = WxPayHelper.toLocalAmount(response.getTotalFee());
        BigDecimal payAmount = WxPayHelper.toLocalAmount(response.getSettlementTotalFee());
        String outTradeNo = response.getOutTradeNo();
        String transactionId = response.getTransactionId();
        TradeQueryResult.TradeStatus tradeStatus = WxPayHelper.getTradeStatus(response.getTradeState());

        return new TradeQueryResult()
            .setTotalAmount(totalAmount)
            .setOutTradeSn(outTradeNo)
            .setTradeSn(transactionId)
            .setPayAmount(payAmount)
            .setTradeStatus(tradeStatus);
    }

    default void tradeClose(String outTradeSn) {
        CloseOrderRequest request = new CloseOrderRequest();
        request.setOutTradeNo(outTradeSn);
        getPaymentService().request(request);
    }

}
