package com.jeiker.demo.web;

import com.jeiker.demo.core.Result;
import com.jeiker.demo.core.ResultGenerator;
import com.jeiker.demo.model.City;
import com.jeiker.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: spring-boot-study
 * Created by jeikerxiao on 2018/6/22 上午11:44
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/save")
    public Result save() {
        City city = new City();
        city.setId(1);
        city.setCountry("国家");
        city.setName("城市名");
        city.setState("省");
        //这里用于测试，key值可以自定义实现
        redisService.set("city:4321", city);
        return ResultGenerator.genSuccessResult(city);
    }

    @RequestMapping("/get")
    public Result get() {
        //这里用于测试，key值可以自定义实现
        City city = (City) redisService.get("city:4312");
        return ResultGenerator.genSuccessResult(city);
    }

}
