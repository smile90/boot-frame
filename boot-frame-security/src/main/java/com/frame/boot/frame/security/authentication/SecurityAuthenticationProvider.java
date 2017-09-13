package com.frame.boot.frame.security.authentication;


import com.frame.boot.frame.security.service.SysUserService;
import com.frame.common.frame.utils.EmptyUtil;
import com.frame.common.frame.utils.EncodeAndDecodeUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("securityAuthenticationProvider")
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "sysUserService")
    private SysUserService sysUserService;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        String password = (String) token.getCredentials();
        if (EmptyUtil.isEmpty(username) || EmptyUtil.isEmpty(password)) {
            throw new BadCredentialsException("用户名/密码无效");
        }
        // 密码加密
        String passwordMd5;
        try {
            passwordMd5 = EncodeAndDecodeUtil.md5(password);
        } catch (Exception e) {
            throw new BadCredentialsException("用户名/密码无效");
        }

        // 账户状态判断
        UserDetails userDetails = sysUserService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户/密码错误");
        } else if (!passwordMd5.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("用户/密码错误");
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }
        // 授权
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}