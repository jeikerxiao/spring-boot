package com.jeiker.demo.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xiao
 * @date : 2018/3/9 上午10:47
 * @description : 数据源配置
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource dataSource1() {
        System.out.println("主配");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "datasource.slave")
    public DataSource dataSource2() {
        System.out.println("从配");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary    //优先使用，多数据源
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource master = dataSource1();
        DataSource slave = dataSource2();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(master);
        //配置多数据源
        Map<Object, Object> map = new HashMap<>();
        map.put(DataSourceType.Master.getName(), master);    //key需要跟ThreadLocal中的值对应
        map.put(DataSourceType.Slave.getName(), slave);
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }
}
