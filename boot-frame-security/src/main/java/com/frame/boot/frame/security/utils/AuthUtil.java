package com.frame.boot.frame.security.utils;

import com.frame.boot.frame.security.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SysUser user = (SysUser) auth.getPrincipal();
            return user.getUsername();
        } else {
            return null;
        }
    }
}
