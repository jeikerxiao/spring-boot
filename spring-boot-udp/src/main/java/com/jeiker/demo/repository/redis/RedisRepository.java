package com.jeiker.demo.repository.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author : xiao
 * @Date : 17/9/30 上午11:20
 */
@Service
public class RedisRepository {

    private static final Logger logger = LoggerFactory.getLogger(RedisRepository.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 保存 string
    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 保存到 list 中
    public Long lpush(String key, String val) throws Exception {
        logger.info("Redis <==== : key:{} - val:{}", key, val);
        return redisTemplate.opsForList().leftPush(key, val);
    }

    // 取出值
    public String rpop(String key) throws Exception {
        return redisTemplate.opsForList().rightPop(key);
    }
}
