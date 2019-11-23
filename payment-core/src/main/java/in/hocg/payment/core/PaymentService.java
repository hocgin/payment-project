package in.hocg.payment.core;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RequiredArgsConstructor
public abstract class PaymentService<T extends ConfigStorage> implements Help {
    
    @Getter
    private final T configStorage;
    
    /**
     * 发起操作请求
     *
     * @param request
     * @return
     */
    public <R extends PaymentResponse,
            P extends PaymentService,
            T extends AbsPaymentRequest<P, R>> R request(@NonNull T request) {
        return request.request((P) this);
    }
    
}
