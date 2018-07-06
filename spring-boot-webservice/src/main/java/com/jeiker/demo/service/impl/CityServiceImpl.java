package com.jeiker.demo.service.impl;

import com.jeiker.demo.service.CityService;
import org.springframework.stereotype.Service;

/**
 * Description: 服务实现
 * Created by jeikerxiao on 2018/7/6 上午11:42
 */
@Service
public class CityServiceImpl implements CityService {

    @Override
    public String sayHi(String user) {
        return user + " hello";
    }

}
