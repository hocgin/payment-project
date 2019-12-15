package in.hocg.payment.wxpay.v1.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class UnifiedOrderResponse
        extends WxPayXmlResponse {
    @XStreamAlias("trade_type")
    private String tradeType;
    @XStreamAlias("prepay_id")
    private String prepayId;
    @XStreamAlias("code_url")
    private String codeUrl;
}
