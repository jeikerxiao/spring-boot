# spring-boot-war

Spring Boot 使用war包，方便使用非嵌入式tomcat布署。

# 环境

* Spring Boot 1.5.9.RELEASE
* IntelliJ IDEA 2017.2

# pom.xml

在pom 文件中的打包方式改为 war 包方式。

```xml
	<groupId>com.jeiker.demo</groupId>
	<artifactId>spring-boot-war</artifactId>
	<version>0.0.1</version>
	<packaging>war</packaging>
```

# ServletInitializer

增加容器初始化类

```java
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWarApplication.class);
	}

}
```

# 运行

```java
mvn package
```

可以看到输出的部署包变成 war 包了。

