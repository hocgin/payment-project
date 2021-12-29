package in.hocg.payment.complete;


import in.hocg.payment.complete.channel.BillData;
import in.hocg.payment.complete.channel.PayChannel;
import in.hocg.payment.complete.channel.TradeOrder;
import in.hocg.payment.complete.docking.jpay.JPayConfig;

import java.util.List;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class UseTests {
    public static void main(String[] args) {
        String useChannelId = "cl666";

        JavaPay.mount(channelId -> new JPayConfig(useChannelId));

        // 支付
        PayChannel useChannel = JavaPay.use(useChannelId);
        useChannel.pay(new TradeOrder());

        // 查询
        useChannel.query("");

        // 查询流水
        List<BillData> billData = useChannel.queryBill();

        // 消息处理
        useChannel.message("")
            .payResult(order -> System.out.println(order));
    }
}
