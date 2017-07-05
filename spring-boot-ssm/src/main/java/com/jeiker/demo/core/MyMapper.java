package com.jeiker.demo.core;

import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 继承自己的MyMapper
 *
 * @Author : xiao
 * @Date : 17/7/4 下午4:13
 */
public interface MyMapper<T> extends
        Mapper<T>,
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>,
        MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
