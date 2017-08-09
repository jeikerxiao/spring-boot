package com.jeiker.demo.annotation;

import com.jeiker.demo.exception.RequestLimitException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author : xiao
 * @Date : 17/8/9 下午4:29
 */
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
