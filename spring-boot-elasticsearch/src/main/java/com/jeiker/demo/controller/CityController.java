package com.jeiker.demo.controller;

import com.jeiker.demo.domain.City;
import com.jeiker.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/6/30 上午11:02
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 添加 城市信息
     * @param city
     * @return
     */
    @PostMapping("/city")
    public Integer createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    /**
     * 查询 城市信息
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    @GetMapping("/city/search")
    public List<City> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return cityService.searchCity(pageNumber, pageSize, searchContent);
    }
}
