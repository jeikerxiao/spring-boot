package com.jeiker.demo.service.impl;

import com.jeiker.demo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Description: spring-boot-webservice
 * Created by jeikerxiao on 2018/7/6 下午2:17
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String user) {
        return user + " hello";
    }
}
