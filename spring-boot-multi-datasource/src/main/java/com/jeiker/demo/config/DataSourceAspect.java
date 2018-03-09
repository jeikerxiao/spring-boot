package com.jeiker.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : xiao
 * @date : 2018/3/9 上午10:46
 * @description : AOP根据注解给上下文赋值
 */
@Aspect
@Order(1)   // 设置AOP执行顺序（需要在事务之前，否则事务只能在默认库中生效）
@Component
public class DataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //切点
    @Pointcut("execution(* com.jeiker.demo.service..*.*(..)))")
    public void aspect() {
    }

    @Before("aspect()")
    private void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?> classz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(JxDataSource.class)) {
                JxDataSource data = m.getAnnotation(JxDataSource.class);
                JdbcContextHolder.putDataSource(data.value().getName());
                logger.info("===============上下文赋值完成:{}", data.value().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

