package in.hocg.payment.wxpay.v1.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v1.response.BatchQueryCommentResponse;
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
public class BatchQueryCommentRequest extends WxPayRequest<BatchQueryCommentResponse> {
    @ApiField(value = "begin_time")
    @XStreamAlias("begin_time")
    protected String beginTime;
    @ApiField(value = "end_time")
    @XStreamAlias("end_time")
    protected String endTime;
    @ApiField(value = "offset")
    @XStreamAlias("offset")
    protected String offset;
    @ApiField(value = "limit")
    @XStreamAlias("limit")
    protected String limit;
    
    @Override
    protected BatchQueryCommentResponse request() {
        return requestXML("billcommentsp/batchquerycomment", BatchQueryCommentResponse.class);
    }
}
