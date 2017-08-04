package com.jeiker.demo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 配置请求头认证过滤器-JWT验证
 *
 * @Author : xiao
 * @Date : 17/8/4 上午9:52
 */
@Component
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    public JsonWebTokenAuthenticationFilter() {
        // 当没有header信息时,不抛异常
        this.setExceptionIfHeaderMissing(false);
        // 在请求头查找认证token
        this.setPrincipalRequestHeader("Authorization");
    }

    /**
     * 认证管理器
     *
     * @param authenticationManager
     */
    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
