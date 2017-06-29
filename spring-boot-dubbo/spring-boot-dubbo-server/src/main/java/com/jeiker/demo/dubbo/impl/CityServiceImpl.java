package com.jeiker.demo.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jeiker.demo.dubbo.CityService;
import com.jeiker.demo.model.City;

/**
 * @Author : xiao
 * @Date : 17/6/29 下午2:59
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0")
public class CityServiceImpl implements CityService{

    @Override
    public City findCityByName(String cityName) {
        return new City(1, "长沙", "湖南", "中国");
    }
}
