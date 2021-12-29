package in.hocg.payment.complete.docking.jpay.option;

import in.hocg.payment.complete.channel.option.PayOption;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class JPayOption implements PayOption {
    private PayType payType = PayType.App;


    public enum PayType {
        App,
        PC,
        QrCode,
        Wap;
    }
}
