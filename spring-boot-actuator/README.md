# spring-boot-actuator

Actuator 是 Spring Boot 提供的对应用系统的自省和监控的集成功能，可以对应用系统进行配置查看、相关功能统计等。

## 依赖

pom.xml

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## 配置

application.properties 

关闭权限控制，使接口都可见，不然会报401-Unauthorized.

```
management.security.enabled=false
```

## Actuator 功能

|HTTP方法|	路径|	描述|	鉴权
|---|---|---|---
|GET|	/autoconfig|	查看自动配置的使用情况|	true
|GET|	/configprops	|查看配置属性，包括默认配置|true
|GET|	/beans	|查看bean及其关系列表	|true
|GET|	/dump	|打印线程栈	|true
|GET|	/env	|查看所有环境变量	|true
|GET|	/env/{name}	|查看具体变量值	|true
|GET|	/health	|查看应用健康指标	|false
|GET|	/info	|查看应用信息	|false
|GET|	/mappings	|查看所有url映射	|true
|GET|	/metrics	|查看应用基本指标	|true
|GET|	/metrics/{name}	|查看具体指标	|true
|POST|	/shutdown	|关闭应用	|true
|GET|	/trace	|查看基本追踪信息	|true

## 运行

GET: 

http://localhost:8080/health

```javascript
{
    "status": "UP",
    "diskSpace": {
        "status": "UP",
        "total": 120108089344,
        "free": 10089127936,
        "threshold": 10485760
    }
}
```

