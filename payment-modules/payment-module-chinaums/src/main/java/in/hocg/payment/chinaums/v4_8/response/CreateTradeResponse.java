package in.hocg.payment.chinaums.v4_8.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CreateTradeResponse extends ChinaUmsPayResponse {
    /**
     * URL
     */
    private String url;
}
