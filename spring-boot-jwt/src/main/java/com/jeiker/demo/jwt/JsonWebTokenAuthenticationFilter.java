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
        // 当 header 头没有信息时,不抛异常
        this.setExceptionIfHeaderMissing(false);

        // 查找 header 中的认证信息
        this.setPrincipalRequestHeader("Authorization");
    }

    /**
     * 设置认证管理器
     */
    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
