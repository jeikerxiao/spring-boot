package com.jeiker.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author : xiao
 * @date : 17/11/21 上午9:19
 * @description :
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Collections.singletonMap("message", "Hello World");
    }
}
