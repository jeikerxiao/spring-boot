package com.jeiker.demo.service.impl;

import com.jeiker.demo.dao.CityMapper;
import com.jeiker.demo.model.City;
import com.jeiker.demo.service.CityService;
import com.jeiker.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @Author : xiao
 * @Date : 2017/07/31
 */
@Service
@Transactional
public class CityServiceImpl extends AbstractService<City> implements CityService {

    @Resource
    private CityMapper cityMapper;

}
