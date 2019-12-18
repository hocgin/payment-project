package in.hocg.payment.spring.boot.sample.controller.alipay;

import in.hocg.payment.spring.boot.sample.AbstractSpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URLDecoder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hocgin on 2019/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class AliPayMessageControllerTest extends AbstractSpringBootTest {
    
    @Test
    public void testMessage() throws Exception {
        final String url = "/alipay/message/trade_status_sync";
        final String content = URLDecoder.decode("gmt_create=2019-12-16+21%3A06%3A57&charset=utf-8&seller_email=bnratf5183%40sandbox.com&subject=iPhone+Xs+Max+256G&sign=c5rTSRMM8su6ma43yldph%2FeLfQzZ9MEq7bmQCvWAkh6Tcce0ms9HurfGgXnQHEpAJX1wnxhyXZPMQ5KBtXb%2B%2F%2F1t7BDUNb6xNCZ%2FKyRsRZJ0l4oOyfiL13VPkwllUymvBEyxFBY3V9VAIY4o%2FwTnz1l9htkT9G4Xm9LJH%2Fe07an9xQLh%2F4dXf6fLebEd%2FcD4pFi%2B%2B8iHbOX6Tjd%2FH56FETlqIP0wS5nzWdmFRILi3ViYn7MRiLmV%2BSmksdR6aGw3BH4B%2BxRfhG6A0Fers%2BKIiFI5zYZ3pCMsOhdRfsWdPA1fC3OJLwFpt0D7%2BMRQa3hoSEK61tx4oGo3aPm%2F1KnMvQ%3D%3D&buyer_id=2088102170344093&invoice_amount=0.01&notify_id=2019121600222210659044091000600620&fund_bill_list=%5B%7B%22amount%22%3A%220.01%22%2C%22fundChannel%22%3A%22ALIPAYACCOUNT%22%7D%5D&notify_type=trade_status_sync&trade_status=TRADE_SUCCESS&receipt_amount=0.01&app_id=2016080300154586&buyer_pay_amount=0.01&sign_type=RSA2&seller_id=2088102169708990&gmt_payment=2019-12-16+21%3A06%3A58&notify_time=2019-12-16+21%3A06%3A59&version=1.0&out_trade_no=157650159962208381158722&total_amount=0.01&trade_no=2019121622001444091000069166&auth_app_id=2016080300154586&buyer_logon_id=lpw***%40sandbox.com&point_amount=0.00");
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(content)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(CoreMatchers.equalTo("trade_status_sync"))
                );
    }
    
}