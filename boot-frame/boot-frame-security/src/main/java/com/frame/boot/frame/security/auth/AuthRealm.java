package com.frame.boot.frame.security.auth;

import com.frame.boot.frame.security.entity.sys.SysModule;
import com.frame.boot.frame.security.entity.sys.SysRole;
import com.frame.boot.frame.security.entity.sys.SysUser;
import com.frame.boot.frame.security.service.sys.SysUserService;
import com.frame.common.frame.utils.EmptyUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        SysUser user = sysUserService.findByUsername(username);
        // 放入shiro. 调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SysUser user = (SysUser) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions = new ArrayList<>();
        List<SysRole> roles = user.getRoles();
        if (EmptyUtil.notEmpty(roles)) {
            for (SysRole role : roles) {
                List<SysModule> modules = role.getModules();
                if (EmptyUtil.notEmpty(modules)) {
                    for (SysModule module : modules) {
                        permissions.add(module.getCode());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 将权限放入shiro中
        info.addStringPermissions(permissions);
        return info;
    }
}