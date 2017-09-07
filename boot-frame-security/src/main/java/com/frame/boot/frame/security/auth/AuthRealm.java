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

        // 放入shiro. 调用CredentialsMatcher检验密码
        SysUser user = sysUserService.findSecurityUserByUsername(username);
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