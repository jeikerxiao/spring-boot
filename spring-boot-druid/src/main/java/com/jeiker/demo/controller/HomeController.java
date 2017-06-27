package com.jeiker.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @Author : xiao
 * @Date : 17/6/27 下午1:27
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> hello() {
        return Collections.singletonMap("message", "Hello World");
    }

}