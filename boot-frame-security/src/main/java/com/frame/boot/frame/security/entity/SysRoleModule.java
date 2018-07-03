package com.frame.boot.frame.security.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;

import java.io.Serializable;
import java.util.Date;

public class SysRoleModule extends Model implements Serializable {

    @TableId
    protected Long id;

    @Version
    protected Long optimistic = 0L;

    protected String createUser;

    protected Date createTime;

    protected String updateUser;

    protected Date updateTime;

    private String roleCode;

    private String moduleCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Long optimistic) {
        this.optimistic = optimistic;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }
}