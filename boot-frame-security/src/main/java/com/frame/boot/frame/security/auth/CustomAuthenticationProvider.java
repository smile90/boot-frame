package com.frame.boot.frame.security.auth;


import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.properties.KaptchaProperties;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.common.frame.utils.EmptyUtil;
import com.frame.common.frame.utils.EncodeAndDecodeUtil;
import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.ServletContextPropertyUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "sysUserService")
    private SysUserService sysUserService;

    @Autowired
    private KaptchaProperties kaptchaProperties;

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        String password = (String) token.getCredentials();

        // 验证码
        if (systemSecurityProperties.isEnableValidCode()) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String sessionValidCode = (String) session.getAttribute(kaptchaProperties.getSessionKey());
            String requestValidCode = request.getParameter("validCode");
            logger.debug("{},{}:", sessionValidCode, requestValidCode);

            // 清空验证码
            session.removeAttribute(kaptchaProperties.getSessionKey());
            // 验证码校验
            if (EmptyUtil.isEmpty(sessionValidCode)|| EmptyUtil.isEmpty(requestValidCode)
                    || !sessionValidCode.trim().equalsIgnoreCase(requestValidCode.trim())) {
                throw new BadCredentialsException(SysConstants.USER_ERROR_MSG_BAD_VALID_CODE);
            }
        }

        // 用户名密码
        if (EmptyUtil.isEmpty(username) || EmptyUtil.isEmpty(password)) {
            throw new BadCredentialsException(SysConstants.USER_ERROR_MSG_BAD_CREDENTIALS);
        }
        // 密码加密
        String passwordMd5;
        try {
            passwordMd5 = EncodeAndDecodeUtil.md5(password);
        } catch (Exception e) {
            throw new BadCredentialsException(SysConstants.USER_ERROR_MSG_BAD_CREDENTIALS);
        }

        // 账户状态判断
        SysUser userDetails = sysUserService.findSecurityUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException(SysConstants.USER_ERROR_MSG_BAD_CREDENTIALS);
        } else if (!passwordMd5.equals(userDetails.getPassword())) {
            throw new BadCredentialsException(SysConstants.USER_ERROR_MSG_BAD_CREDENTIALS);
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException(SysConstants.USER_ERROR_MSG_DISABLED);
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(SysConstants.USER_ERROR_MSG_EXPIRED);
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException(SysConstants.USER_ERROR_MSG_LOCKED);
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new LockedException(SysConstants.USER_ERROR_MSG_CREDENTIALS);
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