

# spring-boot-ramq

Spring Boot 配合 RabbitMQ。

## 安装

Mac下安装

```shell
brew install rabbitmq
```

安装目录：

```shell
/usr/local/sbin
```

启动：

```shell
rabbitmq-server
```

开启Web管理插件

```shell
rabbitmq-plugins enable rabbitmq_management
```

打开浏览器

http://localhost:15672/

账号，密码都为：guest

## 项目配置


application.properties文件中配置rabbitmq:

```java
# rabbitmq configuration
spring.application.name=rabbitmq-test

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

## 运行

运行测试用例：

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRamqApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	public void hello() throws Exception {
		// 生产者: 生产消息
		producer.send();
	}

}
```

打开浏览器查看队列结果：

- http://localhost:15672/

![image](../images/ramq1.png)

查看工程日志输出：

![image](../images/ramq2.png)

