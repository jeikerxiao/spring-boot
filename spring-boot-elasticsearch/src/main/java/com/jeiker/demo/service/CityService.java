package com.jeiker.demo.service;

import com.jeiker.demo.domain.City;

import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/6/30 上午10:59
 */
public interface CityService {

    /**
     * 新增城市信息
     */
    Integer saveCity(City city);

    /**
     * 根据关键词，function score query 权重分分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
