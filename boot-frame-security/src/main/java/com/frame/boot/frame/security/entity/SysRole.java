package com.frame.boot.frame.security.entity;

import com.frame.boot.frame.mybatis.model.BaseModel;
import org.springframework.security.core.GrantedAuthority;
import se.spagettikod.optimist.OptimisticLocking;

import java.util.ArrayList;
import java.util.List;

@OptimisticLocking("sys_role")
public class SysRole extends BaseModel implements GrantedAuthority {

    private String typeCode;

    private String name;

    private String code;

    private List<SysModule> modules = new ArrayList<>();

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public List<SysModule> getModules() {
        return modules;
    }

    public void setModules(List<SysModule> modules) {
        this.modules = modules;
    }

    @Override
    public String getAuthority() {
        return code;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysRole{");
        sb.append("typeCode='").append(typeCode).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}