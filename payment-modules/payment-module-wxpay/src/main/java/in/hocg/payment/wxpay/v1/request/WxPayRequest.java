package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.core.PaymentRequest;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.WxPayService;
import in.hocg.payment.wxpay.v1.response.WxPayResponse;
import in.hocg.payment.wxpay.xml.XStreamInitializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/12/3.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class WxPayRequest<R extends WxPayResponse>
        extends PaymentRequest<WxPayService, R> {
    
    /**
     * APPID
     */
    @ApiField(value = "app_id", required = true)
    @XStreamAlias("app_id")
    protected String appId;
    
    @ApiField(value = "mch_id", required = true)
    @XStreamAlias("mch_id")
    protected String mchId;
    
    @ApiField(value = "nonce_str", required = true)
    @XStreamAlias("nonce_str")
    protected String nonceStr;
    
    @ApiField(value = "sign", required = true, ignore = true)
    @XStreamAlias("sign")
    protected String sign;
    
    @ApiField(value = "sign_type")
    @XStreamAlias("sign_type")
    protected String signType;
    
    public String toXML() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }
}
