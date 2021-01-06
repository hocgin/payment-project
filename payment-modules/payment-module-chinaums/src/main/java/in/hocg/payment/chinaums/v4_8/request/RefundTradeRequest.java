package in.hocg.payment.chinaums.v4_8.request;

import in.hocg.payment.chinaums.v4_8.response.RefundTradeResponse;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by hocgin on 2020/12/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RefundTradeRequest extends ChinaUmsPayRequest<RefundTradeResponse> {
    /**
     * [必须] 订单编号(如果设置orderSn, 则可以忽略)
     */
    @ApiField(value = "merOrderId", required = true)
    private String merOrderId;
    @ApiField(value = "orderSn", ignore = true)
    private String orderSn;
    /**
     * 退费金额 单位：分
     */
    @ApiField(value = "refundAmount", required = true)
    private BigDecimal refundAmount;

    @ApiField(value = "msgType", required = true)
    private String msgType = "refund";

    @Override
    protected RefundTradeResponse request() {
        if (Objects.isNull(merOrderId)) {
            setMerOrderId(getMerOrderId(orderSn));
        }
        return request(RefundTradeResponse.class);
    }
}
