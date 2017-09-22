

# spring-boot-dubbo

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

![image](../images/dubbo.png)

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

