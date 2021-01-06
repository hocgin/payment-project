## 演示/案例

- [Spring Boot 项目](./payment-samples/payment-spring-boot-sample/README.md)
- [支付网关 项目](https://github.com/hocgin/payment-gateway)

## 子模块

| 模块名称 | 模块 |
|---|---|
| 支付宝 | payment-module-alipay |
| 微信支付 | payment-module-wxpay |
| 银联支付 | payment-module-chinaums |

## 使用方式

### Maven

```xml

<xml>
    <!--...-->
    <dependencies>
        <dependency>
            <groupId>in.hocg.payment</groupId>
            <artifactId>payment-module-alipay</artifactId>
            <version>${payment.version}</version>
        </dependency>
        <dependency>
            <groupId>in.hocg.payment</groupId>
            <artifactId>payment-module-wxpay</artifactId>
            <version>${payment.version}</version>
        </dependency>
        <dependency>
            <groupId>in.hocg.payment</groupId>
            <artifactId>payment-module-chinaums</artifactId>
            <version>${payment.version}</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>public</id>
            <name>public</name>
            <url>https://oss.sonatype.org/content/groups/public/</url>
        </repository>
    </repositories>
</xml>
```
