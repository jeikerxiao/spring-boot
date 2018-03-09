package com.jeiker.demo.service.Impl;

import com.jeiker.demo.config.DataSourceType;
import com.jeiker.demo.config.JxDataSource;
import com.jeiker.demo.dao.UserMapper;
import com.jeiker.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : xiao
 * @date : 2018/3/9 上午11:00
 * @description : 用户服务类实现，注意注解使用
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    @JxDataSource(DataSourceType.Master)
    public Integer queryCountByMaster() {
        return userMapper.queryCount();
    }

    @Override
    @JxDataSource(DataSourceType.Slave)
    @Transactional
    public Integer queryCountBySlave() {
        // 测试事务
        userMapper.updateAdminByName();
        Integer rows = userMapper.updateAdminByName();
        // 更新小于1 执行回滚
        if (rows <= 0) {
            throw new RuntimeException();
        }
        return rows;
    }
}
