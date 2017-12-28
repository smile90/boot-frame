package com.frame.boot.frame.cfg.client.test.entity;

public class SysRole extends BaseModel {

    private String typeCode;

    private String name;

    private String code;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysRole{");
        sb.append("typeCode='").append(typeCode).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}