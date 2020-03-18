## 演示/案例
- [Spring Boot 项目](./payment-samples/payment-spring-boot-sample/README.md)

## 使用方式
### Maven
```xml
    <!--...-->
    <dependencies>
        <dependency>
            <groupId>in.hocg.payment</groupId>
            <artifactId>payment-module-alipay</artifactId>
            <version>0.1.1-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>in.hocg.payment</groupId>
            <artifactId>payment-module-wxpay</artifactId>
            <version>0.1.1-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>public</id>
            <name>public</name>
            <url>https://oss.sonatype.org/content/groups/public/</url>
        </repository>
    </repositories>
```
