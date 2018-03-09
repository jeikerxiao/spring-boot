package com.jeiker.demo.config;

/**
 * @author : xiao
 * @date : 2018/3/9 上午10:47
 * @description : 数据库类型
 */
public enum DataSourceType {

    // 主表
    Master("master"),
    // 从表
    Slave("slave");

    private String name;

    private DataSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
