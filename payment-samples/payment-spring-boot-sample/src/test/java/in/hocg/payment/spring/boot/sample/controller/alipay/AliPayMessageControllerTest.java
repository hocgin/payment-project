package in.hocg.payment.spring.boot.sample.controller.alipay;

import in.hocg.payment.spring.boot.sample.AbstractSpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hocgin on 2019/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class AliPayMessageControllerTest extends AbstractSpringBootTest {
    
    @Test
    void testMock2() throws Exception {
        String params = "notify_type=trade_status_sync&notify_id=91722adff935e8cfa58b3aabf4dead6ibe&notify_time=2017-02-16 21:46:15&sign_type=RSA2&sign=WcO+t3D8Kg71dTlKwN7r9PzUOXeaBJwp8/FOuSxcuSkXsoVYxBpsAidprySCjHCjmaglNcjoKJQLJ28/Asl93joTW39FX6i07lXhnbPknezAlwmvPdnQuI01HZsZF9V1i6ggZjBiAd5lG8bZtTxZOJ87ub2i9GuJ3Nr/NUc9VeY=&receipt_amount=null&invoice_amount=null&buyer_pay_amount=null&point_amount=null&voucher_detail_list=null&out_biz_no=null&buyer_logon_id=null&seller_email=null&passback_params=null&out_channel_type=null&trade_no=null&app_id=null&out_trade_no=null&seller_id=null&trade_status=null&total_amount=null&refund_fee=null&subject=null&body=null&gmt_create=null&gmt_payment=null&gmt_refund=null&gmt_close=null&buyer_id=null&fund_bill_list=null&charge_amount=8.88&charge_flags=bluesea_1&settlement_id=2018101610032004620239146945&mdiscount_amount=88.88&discount_amount=88.88";
        final String url = String.format("%s/%s?%s", url(), "alipay/message/test", params);
        
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.ALL))
                // 进行结果验证
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(CoreMatchers.equalTo("worked"))
                );
    }
}