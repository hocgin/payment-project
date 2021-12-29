package in.hocg.payment.complete.channel.vo;

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
public class PayResult {
    private String app;

    private String html;

    private String qrCodeStr;
}
