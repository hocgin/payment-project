package in.hocg.payment.alipay.v2.request.item;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class VoucherDetail {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "amount")
    private String amount;
    @JSONField(name = "merchant_contribute")
    private String merchantContribute;
    @JSONField(name = "other_contribute")
    private String otherContribute;
    @JSONField(name = "memo")
    private String memo;
    @JSONField(name = "template_id")
    private String templateId;
    @JSONField(name = "purchase_buyer_contribute")
    private String purchaseBuyerContribute;
    @JSONField(name = "purchase_merchant_contribute")
    private String purchaseMerchantContribute;
    @JSONField(name = "purchase_ant_contribute")
    private String purchaseAntContribute;
}
