package com.jeiker.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author : xiao
 * @date : 2018/3/9 上午10:47
 * @description :
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("数据源为{}",JdbcContextHolder.getDataSource());
        return JdbcContextHolder.getDataSource();
    }
}
