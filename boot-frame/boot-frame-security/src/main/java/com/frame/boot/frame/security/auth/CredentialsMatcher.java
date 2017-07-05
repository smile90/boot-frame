package com.frame.boot.frame.security.auth;

import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.exception.SecurityException;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.common.frame.base.enums.UserStatus;
import com.frame.common.frame.utils.EmptyUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean pwdCheck = super.doCredentialsMatch(token, info);
        if (!pwdCheck) {
            return pwdCheck;
        } else {
            SysUser user = (SysUser) info.getPrincipals().getPrimaryPrincipal();
            if (user == null) {
                throw new SecurityException(SecurityException.USERNAME_PWD_NOT_SUCC_CODE, "user not found.", SecurityException.USERNAME_PWD_NOT_SUCC_MSG);
            } else if (EmptyUtil.isEmpty(user.getUserStatus())) {
                throw new SecurityException(SecurityException.USER_ERROR_CODE, "user status:empty.", SecurityException.USER_ERROR_MSG);
            } else if (UserStatus.DELETED.name().equals(user.getUserStatus())) {
                throw new SecurityException(SecurityException.USER_ERROR_CODE, "user status:DELETED.", SecurityException.USER_ERROR_MSG);
            } else if (UserStatus.LOCKED.name().equals(user.getUserStatus())) {
                throw new SecurityException(SecurityException.USER_LOCKED_CODE, "user status: LOCKED.", SecurityException.USER_LOCKED_MSG);
            } else if (UserStatus.DISABLED.name().equals(user.getUserStatus())) {
                throw new SecurityException(SecurityException.USER_DISABLED_CODE, "user status: DISABLED.", SecurityException.USER_DISABLED_MSG);
            } else if (UserStatus.EXPIRED.name().equals(user.getUserStatus())) {
                throw new SecurityException(SecurityException.USER_EXPIRED_CODE, "user status: EXPIRED.", SecurityException.USER_EXPIRED_MSG);
            } else {
                // 更新登录时间 last login time
                user.setLastLoginTime(new Date());
                sysUserService.updateByPK(user);
                return true;
            }
        }
    }
}