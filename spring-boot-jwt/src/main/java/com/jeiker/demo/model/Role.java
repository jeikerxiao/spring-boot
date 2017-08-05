package com.jeiker.demo.model;

import java.io.Serializable;

/**
 * @Author : xiao
 * @Date : 17/8/4 下午1:55
 */
public class Role implements Serializable{

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
