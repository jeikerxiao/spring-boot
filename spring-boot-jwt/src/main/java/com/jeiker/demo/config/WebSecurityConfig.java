package com.jeiker.demo.config;

import com.jeiker.demo.jwt.JsonWebTokenSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @Author : xiao
 * @Date : 17/8/4 上午8:57
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends JsonWebTokenSecurityConfig {

}
