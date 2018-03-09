# spring-boot-log4j

Spring Boot 使用log4j2输出日志。

# 环境

* Spring Boot 1.5.7.RELEASE
* IntelliJ IDEA 2017.2

# pom.xml

排除原来logging 依赖，增加log4j2依赖

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

# log4j2.xml 配置

这里依旧使用原始的 xml 配置方式，推荐使用 Spring Boot 的 properties 方式配置。

```xml
<Configuration>
    <Appenders>
        <Console name="Stdout" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss,SSS}:%5p [%40.40c{1.}:%3L] - %m%n"/>
        </Console>
        <Socket name="Socket" host="localhost" port="4560">
            <JsonLayout compact="true" eventEol="true" />
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss,SSS}:%5p [%40.40c{1.}:%3L] - %m%n"/>
        </Socket>
    </Appenders>
    <Loggers>
        <Root level="debug">
            <AppenderRef ref="Stdout"/>
            <AppenderRef ref="Socket"/>
        </Root>
    </Loggers>
</Configuration>
```