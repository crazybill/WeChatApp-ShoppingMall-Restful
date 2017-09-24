package com.leewaiho.togogo.module.sys.security;

import com.leewaiho.togogo.module.sys.security.dto.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/24
 * Project togogo-shixun
 */
public class SecurityUtils {
    
    public static UserInfo getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserInfo) {
                return (UserInfo) authentication.getPrincipal();
            }
        return null;
    }
}
