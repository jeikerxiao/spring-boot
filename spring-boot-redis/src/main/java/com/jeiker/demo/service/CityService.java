package com.jeiker.demo.service;

import com.jeiker.demo.core.Service;
import com.jeiker.demo.model.City;

import java.util.List;


/**
 * @Author : xiao
 * @Date : 2017/07/31
 */
public interface CityService extends Service<City> {

    City insert(City city);

    City updateCity(City city);

    City find(Integer id);

    List<City> findAll();

    void delete(Integer id);

    void deleteAll();
}
