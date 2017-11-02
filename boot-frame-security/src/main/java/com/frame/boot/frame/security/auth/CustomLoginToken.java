package com.frame.boot.frame.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomLoginToken extends UsernamePasswordAuthenticationToken {

    /** 验证码字符串 */
    private String validCode;

    public CustomLoginToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomLoginToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public CustomLoginToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String validCode) {
        super(principal, credentials, authorities);
        this.validCode = validCode;
    }

}
