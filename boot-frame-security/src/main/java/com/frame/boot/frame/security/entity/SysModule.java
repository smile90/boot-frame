package com.frame.boot.frame.security.entity;

import com.frame.boot.frame.mybatis.entity.BaseModel;
import com.frame.common.frame.base.enums.YesNo;

public class SysModule extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String typeCode;

    private String parentCode;

    private String parentPath;

    private String validate = YesNo.Y.name();

    private String useable = YesNo.Y.name();

    private String name;

    private String code;

    private String icon;

    private String url;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate == null ? null : validate.trim();
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysModule{");
        sb.append("typeCode='").append(typeCode).append('\'');
        sb.append(", parentCode='").append(parentCode).append('\'');
        sb.append(", parentPath='").append(parentPath).append('\'');
        sb.append(", validate='").append(validate).append('\'');
        sb.append(", useable='").append(useable).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}