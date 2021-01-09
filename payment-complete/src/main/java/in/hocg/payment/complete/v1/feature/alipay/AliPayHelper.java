package in.hocg.payment.complete.v1.feature.alipay;

import in.hocg.payment.complete.core.TradeQueryResult;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class AliPayHelper {

    public TradeQueryResult.TradeStatus getTradeStatus(String tradeStatus) {
        switch (tradeStatus) {
            case "WAIT_BUYER_PAY": {
                return TradeQueryResult.TradeStatus.WAIT_BUYER_PAY;
            }
            case "TRADE_SUCCESS": {
                return TradeQueryResult.TradeStatus.TRADE_SUCCESS;
            }
            case "TRADE_FINISHED": {
                return TradeQueryResult.TradeStatus.TRADE_FINISHED;
            }
            case "TRADE_CLOSED": {
                return TradeQueryResult.TradeStatus.TRADE_CLOSED;
            }
            default:
                throw new UnsupportedOperationException();
        }
    }
}
