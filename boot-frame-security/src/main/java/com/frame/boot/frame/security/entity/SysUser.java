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

    private String userStatus;

    private String userTypeCode;

    private String typeCode;

    private String email;

    private String telephone;

    private String cellphone;

    private String sex;

    private Integer age;

    private String address;

    private String photo;

    private List<SysRole> roles = new ArrayList<>();

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
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
        return getRoles();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", realname='").append(realname).append('\'');
        sb.append(", userStatus='").append(userStatus).append('\'');
        sb.append(", userTypeCode='").append(userTypeCode).append('\'');
        sb.append(", typeCode='").append(typeCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}