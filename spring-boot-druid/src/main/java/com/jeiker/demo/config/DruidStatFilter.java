package com.jeiker.demo.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @Author : xiao
 * @Date : 17/3/21 上午11:17
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
})
public class DruidStatFilter extends WebStatFilter {
}
