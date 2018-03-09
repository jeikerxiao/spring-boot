package com.jeiker.demo.dao;

import org.apache.ibatis.annotations.Update;

/**
 * @author : xiao
 * @date : 2018/3/9 上午11:01
 * @description :
 */
public interface UserMapper {

    Integer queryCount();

    @Update("update user set age = 11 where username = 'admin'")
    Integer updateAdminByName();
}
