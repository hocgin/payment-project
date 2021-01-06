package in.hocg.payment.chinaums.v4_8.request;

import in.hocg.payment.chinaums.Helpers;
import in.hocg.payment.chinaums.v4_8.ChinaUmsConfigStorage;
import in.hocg.payment.chinaums.v4_8.response.CreateTradeResponse;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.sign.SignValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2021/1/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CreateTradeRequest extends ChinaUmsPayRequest<CreateTradeResponse> {
    /**
     * [必须] 订单编号(如果设置orderSn, 则可以忽略)
     */
    @ApiField(value = "merOrderId", required = true)
    private String merOrderId;
    @ApiField(value = "orderSn", ignore = true)
    private String orderSn;
    /**
     * [必须] 支付方式: trade.h5Pay / WXPay.jsPay
     */
    @ApiField(value = "msgType", required = true)
    private String msgType;
    /**
     * [必须] 订单支付金额 单位:分
     */
    @ApiField(value = "totalAmount", required = true)
    private BigDecimal totalAmount;
    /**
     * 回跳地址
     */
    @ApiField(value = "returnUrl")
    private String returnUrl;
    /**
     * 通知地址
     */
    @ApiField(value = "notifyUrl")
    private String notifyUrl;
    /**
     * 订单描述
     */
    @ApiField(value = "orderDesc")
    private String orderDesc;
    /**
     * 订单过期时间 (默认30分钟)
     */
    @ApiField(value = "expire_time")
    private LocalDateTime expireTime;


    @Override
    protected CreateTradeResponse request() {
        if (Objects.nonNull(merOrderId)) {
            setMerOrderId(getMerOrderId(orderSn));
        }

        ChinaUmsConfigStorage configStorage = getPaymentService().getConfigStorage();
        String baseUrl = configStorage.getUrl();
        Map<String, Object> values = handleRequestParams();
        String httpUrl = URI.create(baseUrl).resolve(getSuffixUrl()).toString();
        SignValue signValue = Helpers.newSignValue().handle(values);
        String url = Helpers.getUrl(httpUrl, signValue.getHandledValues());
        return new CreateTradeResponse().setUrl(url);
    }

    @Override
    protected String getSuffixUrl() {
        return "/netpay-portal/webpay/pay.do";
    }
}
