package in.hocg.payment.complete.docking.alipay;

import in.hocg.payment.PaymentServices;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.request.*;
import in.hocg.payment.alipay.v2.response.*;
import in.hocg.payment.complete.channel.AbsPayChannel;
import in.hocg.payment.complete.channel.BillData;
import in.hocg.payment.complete.channel.TradeOrder;
import in.hocg.payment.complete.channel.option.PayOption;
import in.hocg.payment.complete.channel.vo.PayResult;
import in.hocg.payment.complete.channel.vo.TradeOrderResult;
import in.hocg.payment.complete.docking.jpay.option.JPayOption;
import in.hocg.payment.complete.message.Message;
import in.hocg.payment.utils.AmtUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AliPayChannel extends AbsPayChannel<AliPayConfig> {

    @Override
    public PayResult pay(TradeOrder order, PayOption option) {
        JPayOption opt = (JPayOption) option;
        switch (opt.getPayType()) {
            case App:
                return payWithApp(order, option);
            case PC:
                return payWithPc(order, option);
            case QrCode:
                return payWithQrCode(order, option);
            case Wap:
            default:
        }
        return payWithWap(order, option);
    }

    private PayResult payWithQrCode(TradeOrder order, PayOption option) {
        AliPayConfig config = getConfig();
        String subject = order.getSubject();
        BigDecimal amount = order.getAmount();
        String outOrderNo = order.getOutOrderNo();
        String notifyUrl = config.getNotifyUrl();

        TradePreCreateRequest request = new TradePreCreateRequest();
        request.setBizContent2(new TradeCreateRequest.BizContent()
                .setTotalAmount(AmtUtils.toStr(amount))
                .setSubject(subject)
                .setOutTradeNo(outOrderNo))
            .setNotifyUrl(notifyUrl);
        AliPayService service = getService();
        final TradePreCreateResponse response = service.request(request);
        String qrCode = response.getQrCode();
        PayResult result = new PayResult();
        result.setQrCodeStr(qrCode);
        return result;
    }

    private PayResult payWithPc(TradeOrder order, PayOption option) {
        AliPayConfig config = getConfig();
        String subject = order.getSubject();
        BigDecimal amount = order.getAmount();
        String outOrderNo = order.getOutOrderNo();
        String notifyUrl = config.getNotifyUrl();

        final TradePagePayRequest request = new TradePagePayRequest();
        request.setBizContent2(new TradePagePayRequest.BizContent()
                .setSubject(subject)
                .setTotalAmount(AmtUtils.toStr(amount))
                .setProductCode("FAST_INSTANT_TRADE_PAY")
                .setOutTradeNo(outOrderNo))
            .setNotifyUrl(notifyUrl);
        AliPayService service = getService();

        final TradePagePayResponse response = service.request(request);
        PayResult result = new PayResult();
        return result.setHtml(response.getContent());
    }

    private PayResult payWithWap(TradeOrder order, PayOption option) {
        AliPayConfig config = getConfig();
        String subject = order.getSubject();
        BigDecimal amount = order.getAmount();
        String outOrderNo = order.getOutOrderNo();
        String notifyUrl = config.getNotifyUrl();

        final TradeWapPayRequest tradeWapPayRequest = new TradeWapPayRequest();
        tradeWapPayRequest.setBizContent2(new TradeWapPayRequest.BizContent()
                .setSubject(subject)
                .setTotalAmount(AmtUtils.toStr(amount))
                .setProductCode("QUICK_WAP_WAY")
                .setQuitUrl("http://hocg.in")
                .setOutTradeNo(outOrderNo))
            .setNotifyUrl(notifyUrl);
        AliPayService service = getService();

        final TradeWapPayResponse response = service.request(tradeWapPayRequest);
        PayResult result = new PayResult();
        return result.setHtml(response.getContent());
    }

    private PayResult payWithApp(TradeOrder order, PayOption option) {
        AliPayConfig config = getConfig();
        String subject = order.getSubject();
        BigDecimal amount = order.getAmount();
        String outOrderNo = order.getOutOrderNo();
        String notifyUrl = config.getNotifyUrl();

        final TradeAppPayRequest request = new TradeAppPayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setBizContent2(new TradeAppPayRequest.BizContent()
            .setSubject(subject)
            .setTotalAmount(AmtUtils.toStr(amount))
            .setOutTradeNo(outOrderNo));

        AliPayService service = getService();
        TradeAppPayResponse response = service.request(request);
        PayResult result = new PayResult();
        return result.setApp(response.getContent());
    }

    @Override
    public void close(String outOrderNo) {
        TradeCancelRequest request = new TradeCancelRequest();
        request.setBizContent2(new TradeCancelRequest.BizContent().setOutTradeNo(outOrderNo));
        getService().request(request);
    }

    @Override
    public void refund(String outOrderNo, BigDecimal refundAmt) {
        TradeRefundRequest request = new TradeRefundRequest();
        request.setBizContent2(new TradeRefundRequest.BizContent().setOutTradeNo(outOrderNo)
            .setRefundAmount(AmtUtils.toStr(refundAmt)));
        TradeRefundResponse response = getService().request(request);

    }

    @Override
    public TradeOrderResult query(String orderNo) {
        TradeQueryRequest request = new TradeQueryRequest();
        request.setBizContent2(new TradeQueryRequest.BizContent().setTradeNo(orderNo));
        TradeQueryResponse response = getService().request(request);
        return null;
    }

    @Override
    public List<BillData> queryBill() {
        return null;
    }

    @Override
    public Message message(String msg) {
        return null;
    }

    private AliPayService getService() {
        return PaymentServices.createPaymentService(AliPayService.class, null);
    }
}
