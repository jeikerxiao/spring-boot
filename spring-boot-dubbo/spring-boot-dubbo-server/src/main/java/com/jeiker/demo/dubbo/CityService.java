package com.jeiker.demo.dubbo;

import com.jeiker.demo.model.City;

/**
 * @Author : xiao
 * @Date : 17/6/29 下午2:58
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);
}
