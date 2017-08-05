package com.jeiker.demo.dto;

import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/8/5 上午9:14
 */
public class AuthTokenDTO {

    private String token;
    private Long userId;
    private List<String> resourceList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<String> resourceList) {
        this.resourceList = resourceList;
    }
}
