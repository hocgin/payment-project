package in.hocg.payment.complete.v1.feature.wxpay;

import in.hocg.payment.complete.core.TradeQueryResult;
import in.hocg.payment.wxpay.v2.request.UnifiedOrderRequest;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by hocgin on 2021/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class WxPayHelper {
    public static final BigDecimal LOCAL_AMOUNT = new BigDecimal(100);

    public BigDecimal toWxPayAmount(BigDecimal localAmount) {
        return LOCAL_AMOUNT.multiply(localAmount);
    }

    public BigDecimal toLocalAmount(String wxAmount) {
        return new BigDecimal(wxAmount).divide(LOCAL_AMOUNT, 4, RoundingMode.DOWN);
    }

    public TradeQueryResult.TradeStatus getTradeStatus(String tradeStatus) {
        switch (tradeStatus) {
            case "NOTPAY":
            case "USERPAYING": {
                return TradeQueryResult.TradeStatus.WAIT_BUYER_PAY;
            }
            case "SUCCESS": {
                return TradeQueryResult.TradeStatus.TRADE_SUCCESS;
            }
            case "-": {
                return TradeQueryResult.TradeStatus.TRADE_FINISHED;
            }
            case "REFUND":
            case "PAYERROR":
            case "REVOKED":
            case "CLOSED": {
                return TradeQueryResult.TradeStatus.TRADE_CLOSED;
            }
            default:
                throw new UnsupportedOperationException();
        }
    }

    public UnifiedOrderRequest buildTradeRequest(String tradeType, String title, String outTradeSn,
                                                 String notifyUrl, BigDecimal totalAmount, String buyerIp, String openid) {
        BigDecimal wxTotalAmount = WxPayHelper.toWxPayAmount(totalAmount);
        UnifiedOrderRequest request = new UnifiedOrderRequest();
        request.setOpenId(openid);
        request.setTradeType(tradeType);
        request.setBody(title);
        request.setNotifyUrl(notifyUrl);
        request.setOutTradeNo(outTradeSn);
        request.setTotalFee(String.valueOf(wxTotalAmount));
        request.setSpbillCreateIp(buyerIp);
        return request;
    }
}
