package com.jeiker.demo.model.vo;

import java.io.Serializable;

/**
 * @Author : xiao
 * @Date : 17/7/4 下午3:27
 */
public class PageInfoVo implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
