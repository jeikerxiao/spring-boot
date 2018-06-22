package com.jeiker.demo.service;

/**
 * Description: spring-boot-study
 * Created by jeikerxiao on 2018/6/22 上午11:45
 */
public interface RedisService {

    void set(String key, Object value);

    Object get(String key);
}
