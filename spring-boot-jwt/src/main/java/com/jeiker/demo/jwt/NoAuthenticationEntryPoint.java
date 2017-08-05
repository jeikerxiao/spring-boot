package com.jeiker.demo.jwt;

import com.jeiker.demo.model.RestResp;
import com.jeiker.demo.util.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : xiao
 * @Date : 17/8/4 下午1:53
 */
public class NoAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // 当访问的资源没有权限,会调用这里
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // 返回 json 形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        RestResp restResp = new RestResp(RestResp.NO_SESSION, "没有登录或登录已过期!");

        response.getWriter().println(JsonUtil.toJsonString(restResp));
        response.getWriter().flush();
    }
}
