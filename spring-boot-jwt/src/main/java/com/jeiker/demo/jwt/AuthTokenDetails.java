package com.jeiker.demo.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * session 信息模型
 *
 * @Author : xiao
 * @Date : 17/8/4 上午9:40
 */
public class AuthTokenDetails implements Serializable {

    private Long id;            // 用户id
    private String username;    // 用户登录名
    private String ip;          // 用户ip

    private List<String> roleNames;
    private Date expirationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
