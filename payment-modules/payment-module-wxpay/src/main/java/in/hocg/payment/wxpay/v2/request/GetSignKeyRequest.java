package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.exception.ExceptionFactory;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v2.response.GetSignKeyResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 李华业
 * @date 2020年03月08日 11:17:01
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class GetSignKeyRequest extends WxPayRequest<GetSignKeyResponse> {

    @Override
    protected GetSignKeyResponse request() {
        Boolean isDev = getPaymentService().getConfigStorage().getIsDev();
        if (!isDev) {
            throw ExceptionFactory.wrap("不允许在正式环境调用沙箱接口", null);
        }

        return requestXML("pay/getsignkey", GetSignKeyResponse.class);
    }
}
