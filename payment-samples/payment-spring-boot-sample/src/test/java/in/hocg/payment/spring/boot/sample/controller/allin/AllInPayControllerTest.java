package in.hocg.payment.spring.boot.sample.controller.allin;

import in.hocg.payment.spring.boot.sample.AbstractSpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class AllInPayControllerTest extends AbstractSpringBootTest {
    
    @Test
    public void appPay() throws Exception {
        final String url = "/all-in/pay/app/2/0/orderId";
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(CoreMatchers.anything("app_id"))
                );
    }
}