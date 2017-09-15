package com.frame.boot.frame.security.authentication;

import com.frame.boot.frame.mybatis.model.BaseModel;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.common.frame.base.enums.UserStatus;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SysSecurityUser implements UserDetails {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String username;
    private String realname;
    private String password;
    private String userStatus;
    private List<SysRole> roles = new ArrayList<>();

    public SysSecurityUser(SysUser sysUser) {
        if (sysUser != null) {
            username = sysUser.getUsername();
            realname = sysUser.getRealname();
            password = sysUser.getPassword();
            userStatus = sysUser.getUserStatus();
            roles = sysUser.getRoles();
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !UserStatus.EXPIRED.name().equals(userStatus);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatus.LOCKED.name().equals(userStatus);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 后期启用
        return true;
    }

    @Override
    public boolean isEnabled() {
        try {
            return (!UserStatus.valueOf(userStatus).equals(UserStatus.DISABLED) && !UserStatus.valueOf(userStatus).equals(UserStatus.DELETED));
        } catch (Exception e) {
            logger.error("userStatus error. status:{}", userStatus, e);
            return false;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SysModule> authorities = new ArrayList<>();
        if (EmptyUtil.notEmpty(roles)) {
            for (SysRole role : roles) {
                List<SysModule> modules = role.getModules();
                if (EmptyUtil.notEmpty(modules)) {
                    for (SysModule module : modules) {
                        authorities.add(module);
                    }
                }
            }
        }
        return authorities;
    }
}