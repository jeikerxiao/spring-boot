package com.jeiker.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : xiao
 * @Date : 17/8/4 下午1:55
 */
public class RestResp implements Serializable{

    public static final Integer OK = 200;
    public static final Integer ERROR = 500;
    public static final Integer NO_PERMISSION = 10001;
    public static final Integer NO_SESSION = 10002;
    public static final Integer NOT_FOUND = 404;

    // 默认成功
    private Integer code = OK;

    private String msg;

    private Object data;
    private String path;
    private Date timestamp;

    public RestResp() {

    }

    public RestResp(Object data) {
        this.data = data;
    }

    public RestResp(Integer code, String msg, String path) {
        this.code = code;
        this.msg = msg;
        this.path = path;
        this.timestamp = new Date();
    }

    public RestResp(Integer code, String msg) {
        this(code, msg, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
