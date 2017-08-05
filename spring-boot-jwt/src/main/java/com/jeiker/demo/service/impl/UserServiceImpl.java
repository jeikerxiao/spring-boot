package com.jeiker.demo.service.impl;

import com.jeiker.demo.model.User;
import com.jeiker.demo.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : xiao
 * @Date : 17/8/5 上午9:17
 */
@Service
public class UserServiceImpl implements UserService{

    @Override
    public User login(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("password", DigestUtils.md5Hex(user.getPassword()));

        user.setId(10000L);
        user.setName("xiao");
        return user;
    }

    @Override
    public User get(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("xiao");
        user.setPassword("123456");
        user.setUsername("jeikerxiao");
        return user;
    }
}
