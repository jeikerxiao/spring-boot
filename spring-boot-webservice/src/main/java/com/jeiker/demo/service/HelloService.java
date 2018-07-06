package com.jeiker.demo.service;

import javax.jws.WebService;

/**
 * Description: spring-boot-webservice
 * Created by jeikerxiao on 2018/7/6 下午2:17
 */
@WebService
public interface HelloService {

    String sayHello(String user);
}
