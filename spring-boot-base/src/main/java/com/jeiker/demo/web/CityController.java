package com.jeiker.demo.web;
import com.jeiker.demo.core.Result;
import com.jeiker.demo.core.ResultGenerator;
import com.jeiker.demo.model.City;
import com.jeiker.demo.service.CityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : xiao
 * @Date : 2017/07/31
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Resource
    private CityService cityService;

    @PostMapping("/add")
    public Result add(City city) {
        cityService.save(city);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        cityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(City city) {
        cityService.update(city);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        City city = cityService.findById(id);
        return ResultGenerator.genSuccessResult(city);
    }

    @PostMapping("/list")
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<City> list = cityService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
