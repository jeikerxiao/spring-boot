

# spring-boot-druid

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

