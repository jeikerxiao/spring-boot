package com.jeiker.demo.exception;

/**
 * @Author : xiao
 * @Date : 17/8/5 上午9:13
 */
public class WrongPathException extends RuntimeException {

    public WrongPathException() {
        super("你访问的接口不存在或未配置！");
    }
}
