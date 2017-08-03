package com.jeiker.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @Author : xiao
 * @Date : 17/8/3 上午8:59
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public Map hello() {
        return Collections.singletonMap("message", "Hello World");
    }
}
