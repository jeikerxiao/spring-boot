# 1.spring-boot-elk

Spring Boot 日志输出系统 ELK

这里演示 log4j2.yml 配置方式

# pom.xml 

移除 spring boot 默认的 logging 日志系统，增加 log4j2 日志系统，

同时增加 yaml 文件识别。

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
<!-- 加上这个才能辨认到log4j2.yml文件 -->
<dependency>
	<groupId>com.fasterxml.jackson.dataformat</groupId>
	<artifactId>jackson-dataformat-yaml</artifactId>
</dependency>
```

# log4j2.yml


```yaml
Configuration:
  status: warn

  Properties: # 定义全局变量
    Property: # 缺省配置（用于开发环境）。其他环境需要在VM参数中指定，如下：
      #测试：-Dlog.level.console=warn -Dlog.level.xjj=trace
      #生产：-Dlog.level.console=warn -Dlog.level.xjj=info
      - name: log.level.console
        value: trace
      - name: log.level.com.jeiker.demo
        value: trace
      - name: log.path
        value: ./logs
      - name: project.name
        value: spring-boot-elk

  Appenders:
    Console:  #输出到控制台
      name: CONSOLE
      target: SYSTEM_OUT
      ThresholdFilter:
        level: ${sys:log.level.console} # “sys:”表示：如果VM参数中没指定这个变量值，则使用本文件中定义的缺省全局变量值
        onMatch: ACCEPT
        onMismatch: DENY
      PatternLayout:
        pattern: "%highlight{%d{yyyy-MM-dd HH:mm:ss,SSS}:%5p [%40.40c{1.}:%3L] - %m%n}{FATAL=white, ERROR=red, WARN=blue, INFO=Green, DEBUG=Yellow, TRACE=blue}"
    RollingFile: # 输出到文件, 按日期进行归档
      - name: ROLLING_FILE
        ignoreExceptions: false
        fileName: ${log.path}/${project.name}.log
        filePattern: "${log.path}/$${date:yyyy-MM}/${project.name}-%d{yyyy-MM-dd}.log"
        PatternLayout:
          pattern: "%highlight{%d{yyyy-MM-dd HH:mm:ss,SSS}:%5p [%40.40c{1.}:%3L] - %m%n}{FATAL=white, ERROR=red, WARN=blue, INFO=Green, DEBUG=Yellow, TRACE=blue}"
        Policies:
#          SizeBasedTriggeringPolicy:
#            size: "128 MB"
          TimeBasedTriggeringPolicy:
            interval: 1
            modulate: true
        DefaultRolloverStrategy:
          max: 1000
    Socket:
      name: SOCKET
      host: loalhost
      port: 4560
      protocol: TCP
      ReconnectionDelay: 10000
      PatternLayout:
        pattern: "%d{yyyy-MM-dd HH:mm:ss,SSS}:%5p [%40.40c{1.}:%3L] - %m%n"

  Loggers:
    Root:
      level: info
      AppenderRef:
        - ref: CONSOLE
        - ref: ROLLING_FILE
        - ref: SOCKET
    Logger: # 为com.xjj包配置特殊的Log级别，方便调试
      - name: com.jeiker.demo
        additivity: false
        level: ${sys:log.level.com.jeiker.demo}
        AppenderRef:
          - ref: CONSOLE
          - ref: ROLLING_FILE
          - ref: SOCKET
```
