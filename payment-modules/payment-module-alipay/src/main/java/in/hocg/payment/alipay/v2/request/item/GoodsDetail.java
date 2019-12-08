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
public class GoodsDetail {
    @JSONField(name = "goods_id")
    private String goodsId;
    @JSONField(name = "goods_name")
    private String goodsName;
    @JSONField(name = "quantity")
    private String quantity;
    @JSONField(name = "price")
    private String price;
    @JSONField(name = "goods_category")
    private String goodsCategory;
    @JSONField(name = "categories_tree")
    private String categoriesTree;
    @JSONField(name = "body")
    private String body;
    @JSONField(name = "show_url")
    private String showUrl;
}
