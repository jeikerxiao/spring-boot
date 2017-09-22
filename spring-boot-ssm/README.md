
# spring-boot-ssm

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

![image](../images/ssm.png)

