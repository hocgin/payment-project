package in.hocg.payment.core;


import in.hocg.payment.convert.Convert;
import in.hocg.payment.exception.ExceptionFactory;
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
public abstract class PaymentService<T extends ConfigStorage> {
    
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
            T extends PaymentRequest<P, R>> R request(@NonNull T request) {
        try {
            return request.request((P) this);
        } catch (Exception e) {
            throw ExceptionFactory.wrap("操作失败", e);
        }
    }
    
    /**
     * 消息
     *
     * @param content
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends PaymentMessage> T message(String content, Convert convert, Class<T> clazz) {
        try {
            return PaymentMessage.from(this, convert, content, clazz);
        } catch (Exception e) {
            throw ExceptionFactory.wrap("消息解析失败", e);
        }
    }
    
    public <T extends PaymentMessage> T message(String content, Class<T> clazz) {
        throw new UnsupportedOperationException();
    }
}
