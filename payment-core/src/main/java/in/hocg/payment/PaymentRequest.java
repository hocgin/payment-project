package in.hocg.payment;


import com.alibaba.fastjson.JSON;
import in.hocg.payment.net.HttpClient;
import lombok.Getter;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class PaymentRequest<P extends PaymentService, R extends PaymentResponse> {

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

    /**
     * 获取一个http请求client
     * @return http请求client
     */
    protected abstract HttpClient httpClient();
}
