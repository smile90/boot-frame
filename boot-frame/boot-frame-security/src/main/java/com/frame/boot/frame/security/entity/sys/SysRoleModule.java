package com.frame.boot.frame.security.entity.sys;

import com.frame.boot.frame.mybatis.model.BaseMysqlModel;
import se.spagettikod.optimist.OptimisticLocking;

import java.util.Date;

@OptimisticLocking("sys_role_module")
public class SysRoleModule extends BaseMysqlModel {

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