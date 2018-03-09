package com.jeiker.demo.controller;

import com.jeiker.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xiao
 * @date : 2018/3/9 上午11:00
 * @description :
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/test1")
    public String test1() {
        logger.debug("测试信息：{}", "welcome log world");
        return "主表：" + userService.queryCountByMaster();
    }

    @GetMapping("/test2")
    public String test2() {
        logger.debug("测试信息：{}", "welcome log world");
        return "从表：" + userService.queryCountBySlave();
    }

}
