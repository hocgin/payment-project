package in.hocg.payment.wxpay.v1.message;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import in.hocg.payment.encrypt.AES256Encrypt;
import in.hocg.payment.encrypt.MD5Encrypt;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.Helpers;
import in.hocg.payment.wxpay.v1.WxPayConfigStorage;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Base64;

/**
 * Created by hocgin on 2019/12/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class PayRefundMessage extends WxPayMessage {
    @ApiField(value = "appid", required = true)
    @XStreamAlias("appid")
    private String appId;
    @ApiField(value = "mch_id", required = true)
    @XStreamAlias("mch_id")
    private String mchId;
    @ApiField(value = "nonce_str", required = true)
    @XStreamAlias("nonce_str")
    private String nonceStr;
    @XStreamOmitField
    private ReqInfo reqInfo;
    
    @Data
    @XStreamAlias("root")
    public static class ReqInfo {
        @XStreamAlias("transaction_id")
        private String transactionId;
        @XStreamAlias("out_trade_no")
        private String outTradeNo;
        @XStreamAlias("refund_id")
        private String refundId;
        @XStreamAlias("out_refund_no")
        private String outRefundNo;
        @XStreamAlias("total_fee")
        private String totalFee;
        @XStreamAlias("settlement_total_fee")
        private String settlementTotalFee;
        @XStreamAlias("refund_fee")
        private String refundFee;
        @XStreamAlias("settlement_refund_fee")
        private String settlementRefundFee;
        @XStreamAlias("refund_status")
        private String refundStatus;
        @XStreamAlias("success_time")
        private String successTime;
        @XStreamAlias("refund_recv_accout")
        private String refundRecvAccout;
        @XStreamAlias("refund_account")
        private String refundAccount;
        @XStreamAlias("refund_request_source")
        private String refundRequestSource;
    }
    
    @Override
    public void afterPropertiesSet() {
        decodeReqInfo();
    }
    
    private void decodeReqInfo() {
        WxPayConfigStorage configStorage = getService().getConfigStorage();
        String key = configStorage.getKey();
        key = MD5Encrypt.encode32(key);
        String reqInfo = getXmlValue("xml/req_info");
        byte[] data = Base64.getDecoder().decode(reqInfo);
        String content = AES256Encrypt.decode(data, key);
        XStream xstream = Helpers.newXStream();
        xstream.processAnnotations(ReqInfo.class);
        this.reqInfo = (ReqInfo) xstream.fromXML(content);
    }
    
    @Data
    @Builder
    @XStreamAlias("xml")
    public static class Result implements WxPayMessage.Result {
        @ApiField(value = "return_code", required = true)
        @XStreamAlias("return_code")
        protected String returnCode;
        @ApiField(value = "return_msg", required = true)
        @XStreamAlias("return_msg")
        protected String returnMsg;
    }
}
