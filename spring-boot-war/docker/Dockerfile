# 基镜像 tomcat:8
FROM tomcat:8
# 作者
MAINTAINER jeikerxiao
# 添加spring-boot-war-0.0.1.war 到容器镜像中，并重命名 app.jar
ADD spring-boot-war-0.0.1.war usr/local/tomcat/webapps/app.war
# 运行容器，监听8080端口
EXPOSE 8080
# 启动时运行 tomcat
CMD ["catalina.sh", "run"]