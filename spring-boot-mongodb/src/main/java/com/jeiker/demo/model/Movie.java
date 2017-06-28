package com.jeiker.demo.model;

/**
 * @Author : xiao
 * @Date : 17/6/28 下午1:48
 */
public class Movie {
    private String cid;
    private String name;
    private Double type;
    private Double likes;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getType() {
        return type;
    }

    public void setType(Double type) {
        this.type = type;
    }

    public Double getLikes() {
        return likes;
    }

    public void setLikes(Double likes) {
        this.likes = likes;
    }
}
