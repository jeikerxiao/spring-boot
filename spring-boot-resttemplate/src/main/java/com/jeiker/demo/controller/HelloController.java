package com.jeiker.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : xiao
 * @Date : 17/9/18 下午3:58
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * restTemplate 方式远程调用 服务端接口，消费服务
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        String quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        return quote;
    }

}
