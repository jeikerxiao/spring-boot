package com.jeiker.demo.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author : xiao
 * @Date : 17/8/4 上午9:44
 */
public class JsonWebTokenAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -6855809445272533821L;

    private UserDetails principal;      // 用户信息
    private String jsonWebToken;        // jwt ,返回token

    public JsonWebTokenAuthentication(UserDetails principal, String jsonWebToken) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
