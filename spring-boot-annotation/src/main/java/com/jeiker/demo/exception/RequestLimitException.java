package com.jeiker.demo.exception;

/**
 * @Author : xiao
 * @Date : 17/8/9 下午4:28
 */
public class RequestLimitException extends Exception {

    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
