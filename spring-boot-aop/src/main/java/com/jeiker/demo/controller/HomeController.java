package com.jeiker.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @Author : xiao
 * @Date : 17/8/9 下午3:35
 */
@RestController
@RequestMapping("/test")
public class HomeController {

    @GetMapping("/hello")
    public Map hello() {
        return Collections.singletonMap("data", "hello world!");
    }
}
