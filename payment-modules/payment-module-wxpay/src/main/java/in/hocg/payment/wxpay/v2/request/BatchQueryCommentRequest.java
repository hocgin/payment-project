package in.hocg.payment.wxpay.v2.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import in.hocg.payment.sign.ApiField;
import in.hocg.payment.wxpay.v2.annotation.SafeApi;
import in.hocg.payment.wxpay.v2.response.BatchQueryCommentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/12/11.
 * email: hocgin@gmail.com
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_17&index=11">
 * 拉取订单评价数据
 * </a>
 *
 * @author hocgin
 */
@Data
@SafeApi
@XStreamAlias("xml")
@EqualsAndHashCode(callSuper = true)
public class BatchQueryCommentRequest extends WxPayRequest<BatchQueryCommentResponse> {
    /**
     * [必选] 开始时间
     * 按用户评论时间批量拉取的起始时间，格式为yyyyMMddHHmmss
     */
    @ApiField(value = "begin_time")
    @XStreamAlias("begin_time")
    protected String beginTime;

    /**
     * [必选] 结束时间
     * 按用户评论时间批量拉取的结束时间，格式为yyyyMMddHHmmss
     */
    @ApiField(value = "end_time")
    @XStreamAlias("end_time")
    protected String endTime;

    /**
     * [必选] 位移
     * 指定从某条记录的下一条开始返回记录。接口调用成功时，会返回本次查询最后一条数据的offset。商户需要翻页时，应该把本次调用返回的offset 作为下次调用的入参。注意offset是评论数据在微信支付后台保存的索引，未必是连续的
     */
    @ApiField(value = "offset")
    @XStreamAlias("offset")
    protected String offset;

    /**
     * [可选] 条数
     * 一次拉取的条数, 最大值是200，默认是200
     */
    @ApiField(value = "limit")
    @XStreamAlias("limit")
    protected String limit;

    @Override
    protected BatchQueryCommentResponse request() {
        return requestXML("billcommentsp/batchquerycomment", BatchQueryCommentResponse.class);
    }
}
