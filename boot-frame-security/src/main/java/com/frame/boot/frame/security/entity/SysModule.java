package com.frame.boot.frame.security.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.util.ArrayList;
import java.util.List;
import com.frame.boot.frame.mybatis.entity.BaseModel;
import com.frame.common.frame.base.enums.YesNo;

public class SysModule extends BaseModel {

    private String typeCode;

    private String parentCode;

    private Integer level;

    private String validate = YesNo.Y.name();

    private String useable = YesNo.Y.name();

    private String name;

    private String code;

    private String icon;

    private String url;

    @TableField(exist = false)
    private List<SysFunction> functions = new ArrayList<>();

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public List<SysFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<SysFunction> functions) {
        this.functions = functions;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysModule{");
        sb.append("typeCode='").append(typeCode).append('\'');
        sb.append(", level=").append(level);
        sb.append(", validate='").append(validate).append('\'');
        sb.append(", useable='").append(useable).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}