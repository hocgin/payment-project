package in.hocg.payment.alipay.v2.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TradePreCreateResponse extends AliPayHttpResponse {
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "qr_code")
    private String qrCode;
}
