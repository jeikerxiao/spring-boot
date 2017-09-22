# spring-boot-resttemplate

使用Spring RestTemplate解析RESTful服务。

RestTemplate 简化了发起HTTP请求以及处理响应的过程，并且支持REST。

## 依赖
pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## 示例

GET 请求一个接口：

http://gturnquist-quoters.cfapps.io/api/random

```java
@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * restTemplate 方式远程调用 服务端接口，消费服务
     */
    @GetMapping("/hello")
    public String hello() {
        String quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        return quote;
    }
}
```

## 运行

使用 Postman 测试接口：

GET 方式请求：

http://localhost:8080/test/hello




