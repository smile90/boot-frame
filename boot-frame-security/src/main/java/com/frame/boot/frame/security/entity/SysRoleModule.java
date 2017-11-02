package com.frame.boot.frame.security.entity;

import com.frame.boot.frame.mybatis.model.BaseModel;
import se.spagettikod.optimist.OptimisticLocking;

@OptimisticLocking("sys_role_module")
public class SysRoleModule extends BaseModel {

    private String roleCode;

    private String moduleCode;

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
}