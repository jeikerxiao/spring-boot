package com.jeiker.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author : xiao
 * @date : 17/11/29 下午5:26
 * @description :
 */
@RestController
@RequestMapping("/hi")
public class HelloController {

    @GetMapping("/docker")
    public Map<String, String> hello(){
        return Collections.singletonMap("message", "Hello World");
    }
}
