# 1.spring-boot-annotation

Spring Boot 切面，注解使用。

# 环境

* Spring Boot 1.5.6.RELEASE
* IntelliJ IDEA 2017.2

# Controller

`@RequestLimit(count = 3, time = 10000)` 访问限制注解


```java
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    // 此接口:单个ip ,在10s内, 只能访问3次
    @RequestLimit(count = 3, time = 10000)
    @GetMapping("/test/hello")
    public Map hello(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        result.put("code", "1");
        result.put("message", "success");
        result.put("data", "Hello World!");
        logger.info("===> 接口返回.");
        return result;
    }
}
```

# 注解

RequestLimit

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)//最高优先级
public @interface RequestLimit {
    /**
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}

```

RequestLimitContract

```java
@Aspect
@Component
public class RequestLimitContract {

    private static final Logger logger = LoggerFactory.getLogger(RequestLimitContract.class);

    private Map<String, Integer> redisTemplate=new HashMap<>();

    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
        logger.info("===> 进入切面.");
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null) {
                throw new RequestLimitException("方法中缺失HttpServletRequest参数");
            }
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);

            logger.info("===> 用户IP[{}]访问地址[{}]访问接口", ip, url);

            if(redisTemplate.get(key)==null || redisTemplate.get(key)==0){
                redisTemplate.put(key,1);
            }else{
                redisTemplate.put(key,redisTemplate.get(key)+1);
            }
            int count = redisTemplate.get(key);
            if (count > 0) {
                Timer timer= new Timer();
                TimerTask task  = new TimerTask(){    //创建一个新的计时器任务。
                    @Override
                    public void run() {
                        redisTemplate.remove(key);
                    }
                };
                timer.schedule(task, limit.time());
                //安排在指定延迟后执行指定的任务。task : 所要安排的任务。10000 : 执行任务前的延迟时间，单位是毫秒。
            }
            if (count > limit.count()) {
                logger.info("===> 用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常: ", e);
        }
    }

}
```

# 测试

* GET http://localhost:8080/test/hello