package com.frame.boot.frame.security.auth;

import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.exception.SecurityException;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.common.frame.base.enums.UserStatus;
import com.frame.common.frame.utils.EmptyUtil;
import com.frame.common.frame.utils.EncodeAndDecodeUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    // 认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户输入的token
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        String username = uToken.getUsername();

        SysUser user = sysUserService.findSecurityUserByUsername(username);
        if (user == null) {
            throw new SecurityException(SecurityException.USERNAME_PWD_NOT_SUCC_CODE, "user not found.", SecurityException.USERNAME_PWD_NOT_SUCC_MSG);
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
        }

        // 放入shiro. 调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        // 获取用户
        SysUser user = (SysUser) principal.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions = new ArrayList<>();
        List<String> roles = new ArrayList<>();

        List<SysRole> dbRoles = user.getRoles();
        if (EmptyUtil.notEmpty(roles)) {
            for (SysRole dbRole : dbRoles) {
                roles.add(dbRole.getCode());
                List<SysModule> modules = dbRole.getModules();
                if (EmptyUtil.notEmpty(modules)) {
                    for (SysModule module : modules) {
                        permissions.add(module.getCode());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 将角色、权限放入shiro中
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }
}