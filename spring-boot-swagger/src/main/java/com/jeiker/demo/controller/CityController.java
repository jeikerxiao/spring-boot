package com.jeiker.demo.controller;

import com.jeiker.demo.dao.CityRepository;
import com.jeiker.demo.entity.City;
import io.swagger.annotations.*;
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
@Api(value = "/citys", description = "城市信息操作")
public class CityController {

    @Resource
    private CityRepository cityRepository;

    @ApiOperation(value = "查询城市列表", notes = "获取全部城市列表")
    @GetMapping
    public List<City> getCityList() {
        List<City> cities = new ArrayList<>(cityRepository.findAll());
        return cities;
    }

    @ApiOperation(value = "增加城市信息", notes = "根据City对象增加城市")
    @ApiImplicitParam(name = "city", value = "城市详细实体city", required = true, dataType = "City")
    @PostMapping
    public String postCity(@RequestBody City city) {
        cityRepository.save(city);
        return "success:"+ city.toString();
    }

    @ApiOperation(value = "查询城市信息", notes = "根据url的id来获取城市详细信息")
    @ApiImplicitParam(name = "id", value = "城市ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/{id}")
    public City getCityById(@PathVariable String id) {
        return cityRepository.findOne(Integer.valueOf(id));
    }

    @ApiOperation(value = "修改城市信息", notes = "根据URL中的城市ID更新城市对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "城市ID", required = true, dataType = "String" , paramType = "path"),
            @ApiImplicitParam(name = "city", value = "城市详细实体", required = true, dataType = "City")
    })
    @PutMapping(value = "/{id}")
    public String putCity (@PathVariable String id, @RequestBody City city) {
        City dbCity = cityRepository.findOne(Integer.valueOf(id));
        dbCity.setCountry(city.getCountry());
        dbCity.setName(city.getName());
        dbCity.setState(city.getState());
        cityRepository.saveAndFlush(dbCity);
        return "success:" + dbCity.toString();
    }

    @ApiOperation(value = "删除城市信息", notes = "根据URL城市ID来删除指定对象")
    @ApiImplicitParam(name = "id", value = "城市ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping(value = "/{id}")
    public String deleteCity(@PathVariable String id) {
        cityRepository.delete(Integer.valueOf(id));
        return "success:";
    }
}
