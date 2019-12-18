package in.hocg.payment.spring.boot.sample.controller.wxpay;

import in.hocg.payment.spring.boot.sample.AbstractSpringBootTest;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.v1.message.UnifiedOrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hocgin on 2019/12/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class WxPayMessageControllerTest extends AbstractSpringBootTest {
    
    @Test
    void unifiedOrderMessage() throws Exception {
        final String url = "/wxpay/message/unified-order";
        final String content = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "  <attach><![CDATA[支付测试]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[BCA44E2F60CA374D4B3FAE46E0691DCD0C9F8F4E37147718993A005F48AEA051]]></sign>\n" +
                "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +
                "<coupon_fee_0><![CDATA[10]]></coupon_fee_0>\n" +
                "<coupon_count><![CDATA[1]]></coupon_count>\n" +
                "<coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
                "<coupon_id_0><![CDATA[10000]]></coupon_id_0>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml>";
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(content)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(CoreMatchers.equalTo(Helpers.newXStream()
                                .toXML(UnifiedOrderMessage.Result.builder()
                                        .returnCode("OK")
                                        .returnMsg("SUCCESS")
                                        .build())))
                );
    }
    
    @Test
    void payRefundMessage() throws Exception {
        final String url = "/wxpay/message/pay-refund";
        final String content = "<xml>\n" +
                "<return_code>SUCCESS</return_code>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[TeqClE3i0mvn3DrK]]></nonce_str>\n" +
                "   <req_info><![CDATA[Z2YMvNIShl15ujmiz4r67vAgjzZ6aY9nOE9QmRMKYuPlt3weO2FLodKoht18D5TOpegSecxdRDBr+yyOzV0DEqBUbuiuXwvGKr9cvAC3xHOSSqrCBLFcHOrA62qxJb4euWkYdIvpgmndfQ4XBv2U+N4/bOndlfT6Yh9hUFn6CLhrjomWG4gnRlnSGNcfl8csu0DbixJLZBuf8saXx8OPKgRHCAxERjoCKQLG1ifl2YQnSH3PkVbM+bjbL7ac5U6dShwNv1Ax2iuzhw9mcXUp1PhXkMTREQsCemLEzBVFV7eXTA/FlFKKEZX4wvsM8PQJ1ycmJKz8K0iqZJ+vjx9hjAw6NFH9dAL3Dj9i1OKD6A9rR0vVPSx+vc3csG6WsF1+0nXe0231byJ9K4AxJVIJFhoQcV+CmqKBoGPtNl+7tnZIFoL+6QlZVirItxPIFBPi+jG72twRRDhxXyCEjXwMJ57SfoTvB3T4yDTw7O0TwDHGRrQk2Rabdu7Nu1ZKy35lFdZ7XH/yMfGITZ/RhVwijCyH8NnCIRO0VzopPY3xyLYgUIwTEYCx/K2Gd3GLJEeqazEPJNbe893rxvImgyCQX2CzbLX41rJweAsLCOtkcfwsTTX7knwutGU2LJxsPAEUUspGCXhWw7VHu4ktbmWSLplHemqTrlbkTcV7N+vcSiQH0oHJW/PXy3DqmHXDrDldl0wPxZRSihGV+ML7DPD0CURuguYq5xUfLOuzgigeOVZ7zWe1kdG3F3JyJHoKVsGorKic7pJlwLAThtqFONwF6ciXPiFtf9jLhT7ikv/MI7s5Lw83glI/69LnLLJnP9mNUC9VmNtxU7Cq7gJk7GVaNBw+2qtoUVvhBpGEqhSoi2HGtI9MZWYLI9qrqfqRMcViNTggDF0bShCF+sswh3CbrL4GAjt9dUsDM4nZImYjXB7Ilz4hbX/Yy4U+4pL/zCO7ihJCAK9SYTrZRx0DmU5dgszuILsWTnyY2V5ejbluyUn42itil/P1Wi586why4yivfTEyznkE7mP7iKE7fa4QpNsYJk486dGu+WeCMLcmyUYmo78yzuRNOcSGuBh/z9dL]]></req_info>\n" +
                "</xml>";
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(content)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(CoreMatchers.equalTo(Helpers.newXStream()
                                .toXML(UnifiedOrderMessage.Result.builder()
                                        .returnCode("OK")
                                        .returnMsg("SUCCESS")
                                        .build())))
                );
    }
}