package com.frame.boot.frame.security.entity;

public class SysRoleUser extends BaseModel {

    private String roleCode;

    private String username;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}