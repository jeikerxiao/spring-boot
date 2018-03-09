package com.jeiker.demo.config;

/**
 * @author : xiao
 * @date : 2018/3/9 上午10:48
 * @description :
 */
public class JdbcContextHolder {

    private final static ThreadLocal<String> local = new ThreadLocal<>();

    public static void putDataSource(String name) {
        local.set(name);
    }

    public static String getDataSource() {
        return local.get();
    }
}
