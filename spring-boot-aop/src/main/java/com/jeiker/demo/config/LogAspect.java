package com.jeiker.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : xiao
 * @Date : 17/8/9 下午3:40
 */
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
