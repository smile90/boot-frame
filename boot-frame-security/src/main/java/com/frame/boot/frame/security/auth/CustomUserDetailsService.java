package com.frame.boot.frame.security.auth;

import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysUser;
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
    public SysUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findSecurityUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(SysConstants.USER_ERROR_MSG_BAD_CREDENTIALS);
        }
        return sysUser;
    }
}
