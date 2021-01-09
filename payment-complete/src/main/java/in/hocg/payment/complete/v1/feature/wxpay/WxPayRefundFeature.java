package in.hocg.payment.complete.v1.feature.wxpay;

import in.hocg.payment.complete.core.RefundInfo;
import in.hocg.payment.complete.core.RefundQueryResult;
import in.hocg.payment.complete.v1.feature.PaymentFeature;
import in.hocg.payment.wxpay.v2.request.PayRefundRequest;
import in.hocg.payment.wxpay.v2.request.RefundQueryRequest;
import in.hocg.payment.wxpay.v2.response.RefundQueryResponse;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2021/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface WxPayRefundFeature extends PaymentFeature {

    default void refund(RefundInfo ro) {
        String outTradeSn = ro.getOutTradeSn();
        String outRefundSn = ro.getOutRefundSn();
        String notifyUrl = ro.getNotifyUrl();
        BigDecimal refundAmount = WxPayHelper.toWxPayAmount(ro.getRefundAmount());
        BigDecimal totalAmount = WxPayHelper.toWxPayAmount(ro.getTotalAmount());

        PayRefundRequest request = new PayRefundRequest();
        request.setOutTradeNo(outTradeSn);
        request.setOutRefundNo(outRefundSn);
        request.setTotalFee(String.valueOf(refundAmount));
        request.setRefundFee(String.valueOf(totalAmount));
        request.setNotifyUrl(notifyUrl);
        getPaymentService().request(request);
    }

    default RefundQueryResult refundQuery(String outRefundSn) {
        RefundQueryRequest request = new RefundQueryRequest();
        request.setOutRefundNo(outRefundSn);
        RefundQueryResponse response = getPaymentService().request(request);

        return new RefundQueryResult();
    }

}
