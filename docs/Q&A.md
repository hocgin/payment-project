## Q&A
### 支付宝
- 为什么支付宝不支持`format=XML`？
> 目前没想到限制仅能使用该格式的具体场景。

### 微信支付
- 为什么微信支付使用的`HMAC-SHA256`作为默认签名方式？
> 可手动设置`MD5`方式，但是微信部分接口仅支持`HMAC-SHA256`签名方式，因此推荐使用`HMAC-SHA256`。
- 微信异步通知解密
> https://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters