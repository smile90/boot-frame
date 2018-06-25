package com.frame.boot.frame.security.auth;


import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.exception.FrameSecurityException;
import com.frame.boot.frame.security.properties.KaptchaProperties;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
                throw new FrameSecurityException(FrameSecurityException.VALID_CODE_ERROR_CODE, "valid code is error", FrameSecurityException.VALID_CODE_ERROR_MSG);
            }
        }

        // 用户名密码
        if (EmptyUtil.isEmpty(username) || EmptyUtil.isEmpty(password)) {
            throw new FrameSecurityException(FrameSecurityException.USERNAME_PWD_ERROR_CODE, "username or password is null", FrameSecurityException.USERNAME_PWD_ERROR_MSG);
        }

        // 账户状态判断
        SysUser userDetails = sysUserService.findSecurityUserByUsername(username);
        if (userDetails == null) {
            throw new FrameSecurityException(FrameSecurityException.USERNAME_PWD_ERROR_CODE, "user not found", FrameSecurityException.USERNAME_PWD_ERROR_MSG);
        } else if (!new BCryptPasswordEncoder().encode(password).equals(userDetails.getPassword())) {
            throw new FrameSecurityException(FrameSecurityException.USERNAME_PWD_ERROR_CODE, "password is error", FrameSecurityException.USERNAME_PWD_ERROR_MSG);
        } else if (!userDetails.isEnabled()) {
            throw new FrameSecurityException(FrameSecurityException.USER_DISABLED_CODE, "user not enabled", FrameSecurityException.USER_DISABLED_MSG);
        } else if (!userDetails.isAccountNonExpired()) {
            throw new FrameSecurityException(FrameSecurityException.USER_EXPIRED_CODE, "account non expored", FrameSecurityException.USER_EXPIRED_MSG);
        } else if (!userDetails.isAccountNonLocked()) {
            throw new FrameSecurityException(FrameSecurityException.USER_LOCKED_CODE, "account non locked", FrameSecurityException.USER_LOCKED_MSG);
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new FrameSecurityException(FrameSecurityException.USER_EXPIRED_CODE, "credentials non expored", FrameSecurityException.USER_EXPIRED_MSG);
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