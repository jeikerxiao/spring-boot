package com.jeiker.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeiker.demo.dubbo.CityService;
import com.jeiker.demo.model.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : xiao
 * @Date : 17/6/29 下午3:08
 */
@RestController
public class CityController {

    @Reference(version = "1.0.0")
    CityService cityService;

    @GetMapping("/city")
    public City printCity() {
        String cityName="长沙";
        City city = cityService.findCityByName(cityName);
        return city;
    }
}
