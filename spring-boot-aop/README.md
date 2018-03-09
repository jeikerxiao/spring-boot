# spring-boot-aop

Spring Boot 使用AOP的简单例子。

# pom.xml

增加 Aop 依赖

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

# LogAspect

增加一个围绕 Controller 的切面，增加日志输出。

```java
@Aspect   //定义一个切面
@Configuration
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 定义切点Pointcut
    @Pointcut("execution(* com.jeiker.demo.controller.*Controller.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("===> 执行controller - 前置通知.");
        if(logger.isInfoEnabled()){
            logger.info("===> before: {}", joinPoint);
        }
    }

    /**
     * 环绕通知 用于拦截Controller层记录用户的操作
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        logger.info("===> 执行controller - 环绕通知.");
        if(logger.isInfoEnabled()){
            logger.info("===> around: {}", pjp);
        }

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info("===> 请求开始:");
        logger.info("===> 请求参数: url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        ObjectMapper mapper = new ObjectMapper();
        String resutlStr = mapper.writeValueAsString(result);
        logger.info("===> 请求结束:");
        logger.info("===> 请求结束: Controller返回值是:{}", resutlStr);

        return result;
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        logger.info("===> 执行controller - 后置通知.");
        if(logger.isInfoEnabled()){
            logger.info("===> after: {}", joinPoint);
        }
    }

    /**
     * 配置后置返回通知 使用在方法aspect()上注册的切入点
     *
     * @param joinPoint 切点
     */
    @AfterReturning("controllerAspect()")
    public void afterReturn(JoinPoint joinPoint){
        logger.info("===> 执行controller - 后置返回通知.");
        if(logger.isInfoEnabled()){
            logger.info("===> afterReturn:{} ", joinPoint);
        }
    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing="e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.info("===> 执行controller - 异常通知.");
        if(logger.isInfoEnabled()){
            logger.info("===> afterThrowing: {} ", joinPoint);
        }
    }
}

```

# 测试

访问接口，观察日志输出。

* GET http://localhost:8080/test/hello