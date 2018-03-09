package com.jeiker.demo.service;

/**
 * @author : xiao
 * @date : 2018/3/9 上午11:00
 * @description : 用户服务类接口
 */
public interface UserService {

    Integer queryCountByMaster();

    Integer queryCountBySlave();
}
