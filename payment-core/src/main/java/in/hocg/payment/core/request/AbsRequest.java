package in.hocg.payment.core.request;


import com.alibaba.fastjson.JSON;
import in.hocg.payment.core.response.Response;
import in.hocg.payment.core.utils.StringUtils;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class AbsRequest<P extends PaymentService, R extends Response>
        implements Request {
    @Getter
    private P paymentService;
    
    /**
     * 通过 JSON 来填充
     *
     * @param text
     * @return
     */
    public <T> T fromJson(String text) {
        return ((T) JSON.parseObject(text, this.getClass()));
    }
    
    /**
     * 签名
     *
     * @return
     */
    protected String sign() {
        throw new UnsupportedOperationException(this.getClass().getName() + " 未实现 sign() 函数");
    }
    
    /**
     * 检查签名
     *
     * @param sign
     * @return
     */
    protected boolean checkSign(String sign) {
        return StringUtils.equals(sign(), sign);
    }
    
    /**
     * 请求 - 模版函数
     *
     * @param paymentService
     * @return
     */
    protected R request(P paymentService) {
        this.paymentService = paymentService;
        return this.request();
    }
    
    /**
     * 发起请求
     *
     * @return
     */
    protected abstract R request();
}
