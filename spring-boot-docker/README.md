# spring-boot-docker

Spring Boot 的 使用Docker布署。

# Dockerfile

最主要的Dockerfile 编写

```
# 基镜像java8
FROM java:8

# 作者
MAINTAINER jeikerxiao

# 添加spring-boot-docker-0.0.1.jar 到容器镜像中，并重命名 app.jar
ADD spring-boot-docker-0.0.1.jar app.jar

# 运行容器，监听8080端口
EXPOSE 8080

# 启动时运行 java -jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```