package com.jeiker.demo.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author : xiao
 * @Date : 17/8/4 下午1:58
 */
public class SessionUtil {

    public static Long getCurrUid() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (userDetails != null) {
            return Long.valueOf(userDetails.getUsername());
        }

        return null;

    }

    public static void clear() {

        SecurityContextHolder.clearContext();
    }
}
