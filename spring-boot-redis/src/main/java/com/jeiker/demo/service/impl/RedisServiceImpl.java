package com.jeiker.demo.service.impl;

import com.jeiker.demo.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description: spring-boot-study
 * Created by jeikerxiao on 2018/6/22 上午11:45
 */
@Service
public class RedisServiceImpl implements RedisService {

    //stringRedisTemplate只能缓存key-value的String类型
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
    }

    @Override
    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
}
