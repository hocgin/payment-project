package in.hocg.payment.complete.docking.jpay;


import in.hocg.payment.complete.channel.AbsPayChannel;
import in.hocg.payment.complete.channel.BillData;
import in.hocg.payment.complete.channel.TradeOrder;
import in.hocg.payment.complete.channel.option.PayOption;
import in.hocg.payment.complete.channel.vo.PayResult;
import in.hocg.payment.complete.channel.vo.TradeOrderResult;
import in.hocg.payment.complete.docking.jpay.option.JPayOption;
import in.hocg.payment.complete.message.Message;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/12/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class JPayChannel extends AbsPayChannel<JPayConfig> {

    @Override
    public PayResult pay(TradeOrder order, PayOption option) {
        JPayOption opt = (JPayOption) option;
        if (JPayOption.PayType.App.equals(opt.getPayType())) {


            return new PayResult();
        }
        return new PayResult();
    }

    @Override
    public void close(String outOrderNo) {

    }

    @Override
    public void refund(String outOrderNo, BigDecimal refundAmt) {

    }

    @Override
    public TradeOrderResult query(String outOrderNo) {
        return null;
    }

    @Override
    public List<BillData> queryBill() {
        return Collections.emptyList();
    }

    @Override
    public Message message(String msg) {
        return null;
    }

}
