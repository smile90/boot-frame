package com.frame.common.frame.base.bean;

import com.frame.common.frame.base.enums.SortType;

import java.io.Serializable;

public class SortBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方式
     */
    private SortType sortType;

    /**
     * 默认构造
     */
    public SortBean() {
    }

    /**
     * 正序
     *
     * @param sortField
     */
    public SortBean(String sortField) {
        this.sortField = sortField;
        this.sortType = SortType.ASC;
    }

    /**
     * 排序参数
     *
     * @param sortField
     * @param sortType
     */
    public SortBean(String sortField, SortType sortType) {
        this.sortField = sortField;
        this.sortType = sortType;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SortBean{");
        sb.append("sortField='").append(sortField).append('\'');
        sb.append(", sortType=").append(sortType);
        sb.append('}');
        return sb.toString();
    }
}
