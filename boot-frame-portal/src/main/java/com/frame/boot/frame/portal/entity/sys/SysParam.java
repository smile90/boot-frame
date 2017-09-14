package com.frame.boot.frame.portal.entity.sys;

import com.frame.boot.frame.mybatis.model.BaseModel;
import se.spagettikod.optimist.OptimisticLocking;

/**
 * 系统属性
 */
@OptimisticLocking("sys_param")
public class SysParam extends BaseModel {

    /**
     * 所属类别标识
     */
    private String typeKey;

    /**
     * 名称
     */
    private String name;

    /**
     * 标识
     */
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 排序值
     */
    private Integer orders;

    /**
     * 描述
     */
    private String description;

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
