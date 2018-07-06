package com.frame.boot.frame.security.utils;

import com.frame.boot.frame.security.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static Authentication getUser() {
        if (SecurityContextHolder.getContext() != null) {
            return SecurityContextHolder.getContext().getAuthentication();
        } else {
            return null;
        }
    }

    public static String getUsername() {
        Authentication auth = getUser();
        if (auth != null) {
            SysUser user = (SysUser) auth.getPrincipal();
            return user.getUsername();
        } else {
            return null;
        }
    }
}
