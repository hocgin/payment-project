package in.hocg.payment.complete.v1.feature.alipay;

import in.hocg.payment.alipay.v2.request.TradeFastPayRefundQueryRequest;
import in.hocg.payment.alipay.v2.request.TradeRefundRequest;
import in.hocg.payment.alipay.v2.response.TradeFastPayRefundQueryResponse;
import in.hocg.payment.complete.core.RefundInfo;
import in.hocg.payment.complete.core.RefundQueryResult;
import in.hocg.payment.complete.v1.feature.PaymentFeature;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface AliPayRefundFeature extends PaymentFeature {

    default void refund(RefundInfo ro) {
        String tradeSn = ro.getOutTradeSn();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal refundAmount = ro.getRefundAmount();

        TradeRefundRequest request = new TradeRefundRequest();
        request.setNotifyUrl(notifyUrl);
        request.setBizContent2(new TradeRefundRequest.BizContent()
            .setRefundAmount(String.valueOf(refundAmount))
            .setOutTradeNo(tradeSn));

        getPaymentService().request(request);
    }

    default RefundQueryResult refundQuery(String outRefundSn) {
        TradeFastPayRefundQueryRequest request = new TradeFastPayRefundQueryRequest();
        request.setBizContent2(new TradeFastPayRefundQueryRequest.BizContent()
            .setOutRequestNo(outRefundSn));

        TradeFastPayRefundQueryResponse response = getPaymentService().request(request);

        String tradeNo = response.getTradeNo();
        String outTradeNo = response.getOutTradeNo();
        String totalAmount = response.getTotalAmount();
        String refundAmount = response.getRefundAmount();
        return new RefundQueryResult()
            .setTotalAmount(totalAmount)
            .setRefundAmount(refundAmount)
            .setOutTradeNo(outTradeNo)
            .setTradeNo(tradeNo);
    }

}
