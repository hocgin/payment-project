## 上下文

| 名称 | 描述 |
|---|---|
|tradeSn| 内部单号(第三方平台) |
|outTradeSn| 外部单号(商户单号) |

## 概念

```shell

- 提供商(payment_platform)
    - 支付宝
    - 微信
    - 银联
- 渠道(payment_channel)
    - 支付宝
    - 微信
- 功能(payment_feature)
    - 支付
    - 退费
    - 通知

-| 退费方式(refund_way)
    - 退费
-| 支付方式(payment_way)
    - 二维码支付 = qrcode
    - H5支付 = native
    - APP支付 = app
    - PC网站 = page
-| 通知方式(payment_message)
    - 支付状态
    - 退款状态
```

```java
class Tests {
    public static void main(String[] args) {
        PaymentPlatform.alipay(Config).alipay().wapPay(new TradeInfo());
        PaymentPlatform.alipay(Config).alipay().appPay(new TradeInfo());
        PaymentPlatform.alipay(Config).alipay().refund(new TradeRefundInfo());
        PaymentPlatform.alipay(Config).alipay().refundMessage("接口信息");
    }
}
```

### 功能

- 创建支付
    - 商户单号
    - 支付金额
    - 订单标题
    - 通知地址
- 关闭支付
    - 商户单号
- 查询支付
    - 商户单号
- 创建退款
    - 商户单号
    - 退款金额
- 支付通知
    - 商户单号
    - 支付结果
- 退款通知
    - 商户单号
    - 退款结果

### 设计

```shell
      --> []
--> []       ---> []
      --> []
```

### 配置`概念`

```yaml
boot:
    payment:
        platform:
            alipay:
            wxpay:
            chinaums:

```
