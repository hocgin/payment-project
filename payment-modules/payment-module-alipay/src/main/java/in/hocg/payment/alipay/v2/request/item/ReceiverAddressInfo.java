package in.hocg.payment.alipay.v2.request.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/12/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ReceiverAddressInfo {
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "address")
    private String address;
    @JSONField(name = "mobile")
    private String mobile;
    @JSONField(name = "zip")
    private String zip;
    @JSONField(name = "division_code")
    private String divisionCode;
}
