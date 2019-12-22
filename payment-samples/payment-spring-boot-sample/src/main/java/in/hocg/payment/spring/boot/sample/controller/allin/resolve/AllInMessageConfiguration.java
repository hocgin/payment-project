package in.hocg.payment.spring.boot.sample.controller.allin.resolve;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2019/12/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class AllInMessageConfiguration {
    
    @Bean
    public AllInMessageResolve messageResolve() {
        final AllInMessageResolve messageResolve = new AllInMessageResolve();
        messageResolve.addRule(1, new WxMessageRule());
        return messageResolve;
    }
    
}
