package com.jeiker.demo.controller;

import com.jeiker.demo.entity.City;
import com.jeiker.demo.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author : xiao
 * @Date : 17/3/21 下午6:18
 */
@RestController
@RequestMapping("/citys")
@EnableAutoConfiguration
public class CityController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CityMapper cityMapper;

    @GetMapping("/{id}")
    City findCityById(@PathVariable("id") String id) {

        logger.info("findCityById id = {}", id);

        City city = cityMapper.selectByPrimaryKey(Integer.parseInt(id));
        return city;
    }
}
