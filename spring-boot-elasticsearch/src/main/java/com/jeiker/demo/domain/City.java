package com.jeiker.demo.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author : xiao
 * @Date : 17/6/30 上午11:00
 */
@Document(indexName = "name", type = "city")
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer id;

    private String name;

    private String state;

    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
