package in.hocg.payment.core.service;


import in.hocg.payment.core.storage.ConfigStorage;
import lombok.Getter;
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
    
}
