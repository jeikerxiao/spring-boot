# SpringBootStudy

Spring Boot 的学习项目。

# 1.spring-boot-mybatis

Spring Boot整合MyBatis项目。

测试数据的SQL脚本：

```sql

CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `state` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

BEGIN;
INSERT INTO `city` VALUES ('1', '中国', '北京', '北京市'), ('2', '中国', '广州', '广东省');
COMMIT;

```
在工程的根目录下，可以看到city.sql。包含结构和数据。

配置SQL连接的账号和密码。

## 运行

启动项目,打开浏览器：

### HomeController

- http://localhost:8080/

### CityController

- http://localhost:8080/citys/1

## 单元测试

运行单元测试。

### HomeControllerTest:

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void greeting() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", is("Hello World")));
    }
}
```

### CityControllerTest:

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findCityById() throws Exception {
        mockMvc.perform(get("/citys/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("country", is("中国")))
                .andExpect(jsonPath("name", is("北京")))
                .andExpect(jsonPath("state", is("北京市")));
    }
}
```

# 2.spring-boot-druid

Spring Boot 整合阿里的 druid 数据库连接池。

application.properties 中增加druid 数据库连接池配置。

同时 com.jeiker.demo 下增加config 配置。

## 运行

打开地址：

- http://localhost:8080/druid/login.html

使用config 配置中的账号和密码登录： admin , 123456 。

然后访问：

- http://localhost:8080/citys/1

可看到SQL监控页的执行数据：

- http://localhost:8080/druid/sql.html

# 3.spring-boot-jpa

使用Spring Boot Jpa的 demo.

|方法说明|请求方法|URL|
|-----|----|----|
|获取所有城市列表|GET|http://localhost:8080/citys|
|增加城市信息|POST|http://localhost:8080/citys|
|根据id删除城市信息|DELETE|http://localhost:8080/citys/{id}|
|根据id查询城市信息|GET|http://localhost:8080/citys/{id}|
|根据id修改城市信息|PUT|http://localhost:8080/citys/{id}|

# 4.spring-boot-swagger

Spring Boot 整合 Swagger2。

方法列表如 spring-boot-jpa 项目：

## 运行

swagger接口查看：

- http://localhost:8080/swagger-ui.html

# 5.spring-boot-redis

pom.xml文件中引入redis:

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

application.properties文件中配置redis:

```java
# REDIS (RedisProperties)
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=localhost
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=0
```

测试用例中使用

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedis() throws Exception {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("name", "xiao");
		// 查出字符串
		Assert.assertEquals("xiao", stringRedisTemplate.opsForValue().get("name"));
	}

}
```

本地启动redis:

```shell
redis-server
```

## 运行

运行测试用例。

查看Redis Server里的数据：（这里使用redis界面工具）

![image](images/redis.png)




# 6.spring-boot-ramq

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

![image](images/ramq1.png)

查看工程日志输出：

![image](images/ramq2.png)

# 7.spring-boot-mongodb

Spring Boot 整合 Mongodb


## 项目配置

application.properties文件中配置 mongodb:

```java
spring.data.mongodb.uri=mongodb://root:123456@localhost:27017/mydb

```

## 运行

执行GET 请求

- http://localhost:8080/movie/all

![image](images/mongodb.png)

# 8.spring-boot-dubbo

Spring Boot 整合 Dubbo 的Demo.

需要zookeeper。

使用了spring-boot-starter-dubbo 

- https://github.com/teaey/spring-boot-starter-dubbo

项目分为两个子项目：

spring-boot-dubbo-server（服务端、生产者）

spring-boot-dubbo-client（客户端、消费者）

项目配置：

## spring-boot-dubbo-server

application.properties

```java
# 避免和 client 工程端口冲突
server.port=8081

# Dubbo 服务提供者配置
spring.dubbo.application.name=provider
spring.dubbo.registry.address=zookeeper://192.168.191.32:2181
spring.dubbo.protocol.name=dubbo
spring.dubbo.protocol.port=20880
spring.dubbo.scan=com.jeiker.demo.dubbo

```


## spring-boot-dubbo-client

application.properties

```java
# 避免和 server 工程端口冲突
server.port=8080

# Dubbo 服务消费者配置
spring.dubbo.application.name=consumer
spring.dubbo.registry.address=zookeeper://192.168.191.32:2181
spring.dubbo.scan=com.jeiker.demo.controller

```

## 运行

执行GET 请求

- http://localhost:8080/city

![image](images/dubbo.png)

# 9.spring-boot-elasticsearch

Spring Boot 整合 Elasticsearch 。

注意Elasticsearch版本问题。

|Spring Boot Version (x)|Spring Data Elasticsearch Version (y)|Elasticsearch Version (z)|
|---|---|---|
|x <= 1.3.5	|y <= 1.3.4	|z <= 1.7.2*|
|x >= 1.4.x	|2.0.0 <= y < 5.0.0**|	2.0.0 <= z < 5.0.0**|

假定 Elasticsearch 2.x 服务器已经装好了，地址：192.168.191.32


## 配置

application.properties

```java
# Elasticsearch
spring.data.elasticsearch.repositories.enabled = true
# 默认 9300 是 Java 客户端的端口。9200 是支持 Restful HTTP 的接口。
spring.data.elasticsearch.cluster-nodes = 192.168.191.32:9300
```

## 运行

先添加测试数据：

- POST 
- http://localhost:8080/city

```json
{	
	"id":2,
	"name":"长沙",
	"state":"湖南省",
	"country":"中国"
}
```

查看测试数据：

- GET
- http://localhost:8080/city/search?pageNumber=0&pageSize=10&searchContent=深圳

```json
[
    {
        "id": 1,
        "name": "深圳",
        "state": "广东省",
        "country": "中国"
    },
    {
        "id": 2,
        "name": "长沙",
        "state": "湖南省",
        "country": "中国"
    }
]
```

查看控制台日志输出：

```json
{
  "function_score" : {
    "functions" : [ {
      "filter" : {
        "bool" : {
          "should" : {
            "match" : {
              "cityname" : {
                "query" : "深圳",
                "type" : "boolean"
              }
            }
          }
        }
      },
      "weight" : 1000.0
    }, {
      "filter" : {
        "bool" : {
          "should" : {
            "match" : {
              "description" : {
                "query" : "深圳",
                "type" : "boolean"
              }
            }
          }
        }
      },
      "weight" : 100.0
    } ]
  }
}
```

# 9.spring-boot-ssm

Spring Boot 整合Mybatis 及 相关插件的项目

- Mybatis
- Mapper
- Pagehelper
- Druid
- MySQL
- Swagger2

## 配置

在本地数据库中导入

resources下的 `user_info.sql` 数据库表及测试数据。

修改 `application-dev.properties` 配置文件中的数据库连接。

## 运行

接口1

- GET
- http://localhost:8080/users

接口2

- GET
- http://localhost:8080/users/view/1

![image](images/ssm.png)

# 10.spring-boot-restdoc

用Spring官方推荐的restdoc去生成api文档。

创建一个简单的springboot工程，将http接口通过Api文档暴露出来。

只需要通过 JUnit单元测试和spring的MockMVC就可以生成文档。

## pom.xml依赖

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.restdocs</groupId>
		<artifactId>spring-restdocs-mockmvc</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

pom.xml插件

```xml
<plugins>

	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
	</plugin>

	<plugin>
		<groupId>org.asciidoctor</groupId>
		<artifactId>asciidoctor-maven-plugin</artifactId>
		<version>1.5.3</version>
		<executions>
			<execution>
				<id>generate-docs</id>
				<phase>prepare-package</phase>
				<goals>
					<goal>process-asciidoc</goal>
				</goals>
				<configuration>
					<backend>html</backend>
					<doctype>book</doctype>
					<!-- 配置模板位置 {snippets} -->
					<attributes>
						<snippets>${project.build.directory}/snippets</snippets>
					</attributes>
				</configuration>
			</execution>
		</executions>
		<dependencies>
			<dependency>
				<groupId>org.springframework.restdocs</groupId>
				<artifactId>spring-restdocs-asciidoctor</artifactId>
				<version>1.2.1.RELEASE</version>
			</dependency>
		</dependencies>
	</plugin>

</plugins>
```

## 单元测试类

```java
// 提供Spring测试上下文
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        // 建立web请求
        this.mockMvc.perform(get("/"))
                // 打印结果
                .andDo(print())
                // 检查状态码为200
                .andExpect(status().isOk())
                // 检查内容有"Hello World"
                .andExpect(content().string(containsString("Hello World")))
                // 检查在根目录下有一个名为name的节点，并且该节点对应的值是“Hello World”
                .andExpect(jsonPath("$.message").value("Hello World"))
                // 生成文件目录名称:home
                .andDo(document("home"));
    }
}
```

## 运行单元测试

启动单元测试，测试通过，你会发现在target文件下生成了一个snippets文件夹，其目录结构如下:

```
└── target
    └── snippets
        └── home
            └── httpie-request.adoc
            └── curl-request.adoc
            └── http-request.adoc
            └── http-response.adoc
```

## 使用snippets

创建一个新文件src/main/asciidoc/index.adoc

```
= 用 Spring REST Docs 构建接口文档

API服务器地址 http://localhost:8080:

.请求
include::{snippets}/home/http-request.adoc[]

.响应
include::{snippets}/home/http-response.adoc[]

这个例子非常简单，通过单元测试和一些简单的配置就能够得到api文档了。
```

## 生成API文档

这时只需要通过maven 打包命令就可以生成文档了：


```shell
$ mvn package
```

在/target/generated-docs下有个index.html，打开这个html,显示如下:

![image](images/restdoc.png)

# 11.spring-boot-jwt

JWT是 Json Web Token 的缩写。

## JWT的工作流程

1. 用户导航到登录页，输入用户名、密码，进行登录
2. 服务器验证登录鉴权，如果改用户合法，根据用户的信息和服务器的规则生成JWT Token
服务器将该token以json形式返回（不一定要json形式，这里说的是一种常见的做法）
3. 用户得到token，存在localStorage、cookie或其它数据存储形式中。
4. 以后用户请求受保护的API时，在请求的header中加入 Authorization: Bearer xxxx(token)。此处注意token之前有一个7字符长度的 Bearer
5. 服务器端对此token进行检验，如果合法就解析其中内容，根据其拥有的权限和自己的业务逻辑给出对应的响应结果。
6. 用户取得结果

## 运行

### 登录

![image](images/jwt1.png)


### 获取用户信息

![image](images/jwt2.png)