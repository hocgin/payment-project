package in.hocg.payment.parser;

/**
 * Created by hocgin on 2019/12/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Data {
    public static String XML_DATA_ALIPAY = "<koubei_trade_itemorder_query_response>\n" +
            "<code>10000</code>\n" +
            "<msg>Success</msg>  " +
            "<order_no>20160831001040010900010000000001</order_no>\n" +
            "  <out_order_no>20160831001040010900010000000001</out_order_no>\n" +
            "  <partner_id>20180000000001</partner_id>\n" +
            "  <trade_no>2017122021001004560209558948</trade_no>\n" +
            "  <status>CLOSE</status>\n" +
            "  <buyer_id>2088102164040745</buyer_id>\n" +
            "  <biz_product>ONLINE_TRADE_PAY</biz_product>\n" +
            "  <gmt_create>2016-09-29 00:00:00</gmt_create>\n" +
            "  <seller_id>208810000003401</seller_id>\n" +
            "  <gmt_payment>2018-01-12 21:10:10</gmt_payment>\n" +
            "  <gmt_modified>2016-09-29 00:00:00</gmt_modified>\n" +
            "  <total_amount>25.00</total_amount>\n" +
            "  <real_pay_amount>20.00</real_pay_amount>\n" +
            "  <discount_amount>5.00</discount_amount>\n" +
            "  <deliver_seller_real_amount>5.00</deliver_seller_real_amount>\n" +
            "  <item_order_vo list=\"true\">\n" +
            "    <item_order_v_o>\n" +
            "      <item_order_no>2015060400076000000000000000</item_order_no>\n" +
            "      <sku_id>2015060400076000000000012100</sku_id>\n" +
            "      <quantity>2</quantity>\n" +
            "      <price>10.00</price>\n" +
            "      <ext_info>{externalVoucherCode=[A12345,B98765]}</ext_info>\n" +
            "    </item_order_v_o>\n" +
            "  </item_order_vo>\n" +
            "</koubei_trade_itemorder_query_response>";
}
