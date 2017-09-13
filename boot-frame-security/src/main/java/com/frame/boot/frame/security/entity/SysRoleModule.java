package com.frame.boot.frame.security.entity;

import com.frame.boot.frame.mybatis.model.BaseModel;
import se.spagettikod.optimist.OptimisticLocking;

@OptimisticLocking("sys_role_module")
public class SysRoleModule extends BaseModel {

    private Long roleId;

    private Long moduleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

}