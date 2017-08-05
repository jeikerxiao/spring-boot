package com.jeiker.demo.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : xiao
 * @Date : 17/8/4 上午9:52
 */
@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticationUser = null;
        // 只处理 PreAuthenticatedAuthenticationToken
        if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                && authentication.getPrincipal() != null) {
            String tokenHeader = (String) authentication.getPrincipal();
            UserDetails userDetails = parseToken(tokenHeader);
            if (userDetails != null) {
                authenticationUser = new JsonWebTokenAuthentication(userDetails, tokenHeader);
            }
        } else {
            // 已经有 JsonWebTokenAuthentication
            authenticationUser = authentication;
        }
        return authenticationUser;
    }

    private UserDetails parseToken(String tokenHeader) {
        UserDetails principal = null;
        AuthTokenDetails authTokenDetails = tokenService.parseAndValidate(tokenHeader);

        if (authTokenDetails != null) {
            List<GrantedAuthority> authorities = authTokenDetails.getRoleNames()
                    .stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            // userId 介入 Spring Security
            principal = new User(authTokenDetails.getId().toString(), "", authorities);
        }
        return principal;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class )||
               authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
    }
}
