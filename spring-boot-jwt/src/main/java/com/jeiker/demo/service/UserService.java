package com.jeiker.demo.service;

import com.jeiker.demo.model.User;

/**
 * @Author : xiao
 * @Date : 17/8/5 上午9:17
 */
public interface UserService {

    /**
     * 用户登录
     */
    User login(User user);


    /**
     * 获得用户对象
     */
    User get(Long id);
}
