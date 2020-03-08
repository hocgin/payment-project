package in.hocg.payment.wxpay.v2.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 李华业
 * @date 2020年03月08日 11:17:29
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class GetSignKeyResponse extends WxPayXmlResponse{
    @XStreamAlias("sandbox_signkey")
    private String key;

    @Override
    public boolean isSign() {
        return false;
    }
}
