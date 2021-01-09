package in.hocg.payment.complete.v1.feature.alipay;

import in.hocg.payment.alipay.v2.request.TradeAppPayRequest;
import in.hocg.payment.alipay.v2.request.TradeCloseRequest;
import in.hocg.payment.alipay.v2.request.TradePagePayRequest;
import in.hocg.payment.alipay.v2.request.TradePreCreateRequest;
import in.hocg.payment.alipay.v2.request.TradeQueryRequest;
import in.hocg.payment.alipay.v2.request.TradeWapPayRequest;
import in.hocg.payment.alipay.v2.response.TradeAppPayResponse;
import in.hocg.payment.alipay.v2.response.TradePagePayResponse;
import in.hocg.payment.alipay.v2.response.TradePreCreateResponse;
import in.hocg.payment.alipay.v2.response.TradeQueryResponse;
import in.hocg.payment.alipay.v2.response.TradeWapPayResponse;
import in.hocg.payment.complete.core.TradeInfo;
import in.hocg.payment.complete.core.TradeQueryResult;
import in.hocg.payment.complete.v1.feature.PaymentFeature;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface AliPayPaymentFeature extends PaymentFeature {

    default String tradeAppPay(TradeInfo ro) {
        String title = ro.getTitle();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal totalAmount = ro.getTotalAmount();
        String outTradeSn = ro.getOutTradeSn();

        TradeAppPayRequest request = new TradeAppPayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setBizContent2(new TradeAppPayRequest.BizContent()
            .setSubject(title)
            .setTotalAmount(String.valueOf(totalAmount))
            .setOutTradeNo(outTradeSn));
        TradeAppPayResponse response = getPaymentService().request(request);
        return response.getContent();
    }

    default String tradePreCreate(TradeInfo ro) {
        String title = ro.getTitle();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal totalAmount = ro.getTotalAmount();
        String outTradeSn = ro.getOutTradeSn();

        TradePreCreateRequest request = new TradePreCreateRequest();
        request.setNotifyUrl(notifyUrl);
        request.setBizContent2(new TradePreCreateRequest.BizContent()
            .setSubject(title)
            .setTotalAmount(String.valueOf(totalAmount))
            .setOutTradeNo(outTradeSn));
        TradePreCreateResponse response = getPaymentService().request(request);
        return response.getContent();
    }

    default String tradeWapPay(TradeInfo ro) {
        String title = ro.getTitle();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal totalAmount = ro.getTotalAmount();
        String outTradeSn = ro.getOutTradeSn();
        String quitUrl = ro.getQuitUrl();

        TradeWapPayRequest request = new TradeWapPayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setBizContent2(new TradeWapPayRequest.BizContent()
            .setSubject(title)
            .setTotalAmount(String.valueOf(totalAmount))
            .setQuitUrl(quitUrl)
            .setProductCode("QUICK_WAP_WAY")
            .setOutTradeNo(outTradeSn));
        TradeWapPayResponse response = getPaymentService().request(request);
        return response.getContent();
    }

    default String tradePagePay(TradeInfo ro) {
        String title = ro.getTitle();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal totalAmount = ro.getTotalAmount();
        String outTradeSn = ro.getOutTradeSn();

        TradePagePayRequest request = new TradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setBizContent2(new TradePagePayRequest.BizContent()
            .setSubject(title)
            .setTotalAmount(String.valueOf(totalAmount))
            .setProductCode("FAST_INSTANT_TRADE_PAY")
            .setOutTradeNo(outTradeSn));
        TradePagePayResponse response = getPaymentService().request(request);
        return response.getContent();
    }

    default TradeQueryResult tradeQuery(String outTradeSn) {
        TradeQueryRequest request = new TradeQueryRequest();
        request.setBizContent2(new TradeQueryRequest.BizContent()
            .setOutTradeNo(outTradeSn));
        TradeQueryResponse response = getPaymentService().request(request);

        String outTradeNo = response.getOutTradeNo();
        String tradeNo = response.getTradeNo();
        BigDecimal totalAmount = new BigDecimal(response.getTotalAmount());
        BigDecimal payAmount = new BigDecimal(response.getPayAmount());
        TradeQueryResult.TradeStatus tradeStatus = AliPayHelper.getTradeStatus(response.getTradeStatus());

        return new TradeQueryResult()
            .setTotalAmount(totalAmount)
            .setOutTradeSn(outTradeNo)
            .setTradeSn(tradeNo)
            .setPayAmount(payAmount)
            .setTradeStatus(tradeStatus);
    }

    default void tradeClose(String outTradeSn) {
        TradeCloseRequest request = new TradeCloseRequest();
        request.setBizContent2(new TradeQueryRequest.BizContent()
            .setOutTradeNo(outTradeSn));
        getPaymentService().request(request);
    }

}
