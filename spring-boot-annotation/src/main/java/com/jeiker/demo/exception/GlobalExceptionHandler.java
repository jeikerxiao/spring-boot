package com.jeiker.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : xiao
 * @Date : 17/8/9 下午4:52
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(RequestLimitException.class)
    public Map exceptionHandler(RuntimeException e, HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();
        result.put("code", "1001");
        result.put("message", "HTTP请求超出设定的限制");
        logger.info("===> 异常处理返回.");
        return result;
    }
}
