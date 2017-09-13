package com.frame.boot.frame.security.entity;

import com.frame.boot.frame.mybatis.model.BaseModel;
import com.frame.common.frame.base.enums.UserStatus;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.spagettikod.optimist.OptimisticLocking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@OptimisticLocking("sys_user")
public class SysUser extends BaseModel implements UserDetails {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String username;

    private String password;

    private String realname;

    private Date lastLoginTime;

    private String lastLoginIp;

    private String lastPassword;

    private String userStatus;

    private String userTypeCode;

    private String typeCode;

    private List<SysRole> roles = new ArrayList<>();

    @Override
    public boolean isAccountNonExpired() {
        return !UserStatus.EXPIRED.name().equals(status);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatus.LOCKED.name().equals(status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 后期启用
        return true;
    }

    @Override
    public boolean isEnabled() {
        try {
            return (!UserStatus.valueOf(status).equals(UserStatus.DISABLED) && !UserStatus.valueOf(status).equals(UserStatus.DELETED));
        } catch (Exception e) {
            logger.error("userStatus error. status:{}", status, e);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword == null ? null : lastPassword.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(String userTypeCode) {
        this.userTypeCode = userTypeCode == null ? null : userTypeCode.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}