package in.hocg.payment.chinaums.v4_8.request;

import in.hocg.payment.chinaums.v4_8.response.QueryTradeResponse;
import in.hocg.payment.sign.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * Created by hocgin on 2021/1/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QueryTradeRequest extends ChinaUmsPayRequest<QueryTradeResponse> {
    /**
     * [必须] 订单编号(如果设置orderSn, 则可以忽略)
     */
    @ApiField(value = "merOrderId", required = true)
    private String merOrderId;
    @ApiField(value = "orderSn", ignore = true)
    private String orderSn;

    @ApiField(value = "msgType", required = true)
    private String msgType = "query";

    @Override
    protected QueryTradeResponse request() {
        if (Objects.isNull(merOrderId)) {
            setMerOrderId(getMerOrderId(orderSn));
        }
        return request(QueryTradeResponse.class);
    }
}
