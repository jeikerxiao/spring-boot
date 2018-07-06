package com.jeiker.demo.service;

import javax.jws.WebService;

/**
 * Description: webservice 接口
 * Created by jeikerxiao on 2018/7/6 上午11:42
 */
//@WebService(
//    serviceName = "CityService", // 与接口中指定的name一致
//    targetNamespace = "http://service.demo.jeiker.com", // 与接口中的命名空间一致,一般是接口的包名倒
//    endpointInterface = "com.jeiker.demo.service.CityService"// 接口地址
//)
@WebService
public interface CityService {

    String sayHi(String user);

}
