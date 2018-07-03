package com.frame.boot.frame.security.auth;

import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.exception.SystemSecurityException;
import com.frame.boot.frame.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public SysUser loadUserByUsername(String username) throws RuntimeException {
        SysUser sysUser = sysUserService.findSecurityUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("user not found", new SystemSecurityException(SysConstants.USERNAME_PWD_ERROR_CODE, "user not found", SysConstants.USERNAME_PWD_ERROR_MSG));
        }
        return sysUser;
    }
}
