package in.hocg.payment.alipay.v2.request.inner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by hocgin on 2019/11/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class GoodsDetail {
    @NonNull
    @JSONField(name = "goods_id")
    private String goodsId;
    
    @NonNull
    @JSONField(name = "goods_name")
    private String goodsName;
    
    @NonNull
    @JSONField(name = "quantity")
    private String quantity;
    
    @NonNull
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
    
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
