package com.frame.common.frame.base.params;

import com.frame.common.frame.base.enums.DataType;
import com.frame.common.frame.base.enums.SearchType;

public class SearchParam extends RequestParam {

    private static final long serialVersionUID = 1L;

    /** 查询的字段名称 */
    private String name;

    /** 查询条件的类别 */
    private SearchType type;

    /** 查询的字段类别 */
    private DataType valueType;

    /** 查询的字段值 */
    private String value;

    public SearchParam() {}

    public SearchParam(String name, SearchType type, DataType valueType, String value) {
        this.name = name;
        this.type = type;
        this.valueType = valueType;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SearchType getType() {
        return type;
    }

    public void setType(SearchType type) {
        this.type = type;
    }

    public DataType getValueType() {
        return valueType;
    }

    public void setValueType(DataType valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SearchParam{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", valueType=").append(valueType);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
