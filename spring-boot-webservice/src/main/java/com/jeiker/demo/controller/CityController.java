package com.jeiker.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author : xiao
 * @date : 17/11/2 上午11:01
 * @description :
 */
@RestController
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @RequestMapping("/hi")
    public Map<String, String> say() {
        return Collections.singletonMap("message", "hello");
    }
}
