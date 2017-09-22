

# spring-boot-mongodb

Spring Boot 整合 Mongodb


## 项目配置

application.properties文件中配置 mongodb:

```java
spring.data.mongodb.uri=mongodb://root:123456@localhost:27017/mydb

```

## 运行

执行GET 请求

- http://localhost:8080/movie/all

![image](../images/mongodb.png)

