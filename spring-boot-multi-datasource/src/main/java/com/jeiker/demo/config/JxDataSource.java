package com.jeiker.demo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : xiao
 * @date : 2018/3/9 上午10:48
 * @description : 数据源选择 - 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JxDataSource {

    DataSourceType value() default DataSourceType.Master;	//默认主表

}
