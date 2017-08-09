package com.jeiker.demo.controller;

import com.jeiker.demo.annotation.RequestLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : xiao
 * @Date : 17/8/9 下午4:31
 */
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
