package com.jeiker.demo.controller;

import com.jeiker.demo.dao.CityRepository;
import com.jeiker.demo.entity.City;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/3/20 下午2:05
 */
@RestController
@RequestMapping("/citys")
public class CityController {

    @Resource
    private CityRepository cityRepository;

    /**
     * 获取所有城市列表
     */
    @GetMapping
    public List<City> getCityList() {
        List<City> cities = new ArrayList<>(cityRepository.findAll());
        return cities;
    }

    /**
     * 新增城市
     */
    @PostMapping
    public String postCity(@RequestBody City city) {
        cityRepository.save(city);
        return "success:"+ city.toString();
    }

    /**
     * 根据id,获取指定城市
     */
    @GetMapping(value = "/{id}")
    public City getCityById(@PathVariable String id) {
        return cityRepository.findOne(Integer.valueOf(id));
    }

    /**
     * 根据id,修改城市信息
     */
    @PutMapping(value = "/{id}")
    public String putCity (@PathVariable String id, @RequestBody City city) {
        City dbCity = cityRepository.findOne(Integer.valueOf(id));
        dbCity.setCountry(city.getCountry());
        dbCity.setName(city.getName());
        dbCity.setState(city.getState());
        cityRepository.saveAndFlush(dbCity);
        return "success:" + dbCity.toString();
    }

    /**
     * 删除城市
     */
    @DeleteMapping(value = "/{id}")
    public String deleteCity(@PathVariable String id) {
        cityRepository.delete(Integer.valueOf(id));
        return "success:";
    }
}
