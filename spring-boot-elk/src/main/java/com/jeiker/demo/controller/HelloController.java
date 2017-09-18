package com.jeiker.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @Author : xiao
 * @Date : 17/9/18 下午3:09
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public Map<String, String> hello() {
        for (int i = 0; i < 10; i++) {
            log.info("===> 输出info日志。");
            log.debug("===> 输出debug日志。");
            log.error("===> 输出error日志。");
        }
        return Collections.singletonMap("message", "Hello World");
    }
}
