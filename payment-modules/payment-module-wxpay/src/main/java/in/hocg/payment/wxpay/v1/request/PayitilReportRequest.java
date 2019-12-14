package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.PayitilReportResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class PayitilReportRequest extends WxPayRequest<PayitilReportResponse> {
    @ApiField(value = "interface_url")
    @XStreamAlias("interface_url")
    protected String interfaceUrl;
    @ApiField(value = "execute_time")
    @XStreamAlias("execute_time")
    protected String executeTime;
    @ApiField(value = "out_trade_no")
    @XStreamAlias("out_trade_no")
    protected String outTradeNo;
    @ApiField(value = "user_ip")
    @XStreamAlias("user_ip")
    protected String userIp;
    @ApiField(value = "time")
    @XStreamAlias("time")
    protected String time;
    
    @Override
    protected PayitilReportResponse request() {
        return request("payitil/report", PayitilReportResponse.class);
    }
}
