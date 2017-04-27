package com.frame.boot.frame.hibernate.search;

import com.frame.boot.frame.hibernate.search.enums.DataType;
import com.frame.boot.frame.hibernate.search.enums.SearchType;

/**
 * 查询数据
 * @author duancq
 * 2016年8月27日 下午1:19:43
 */
public class SearchData {

	/** 查询的字段名称 */
	private String name;

	/** 查询条件的类别 */
	private SearchType type;

	/** 查询的字段类别 */
	private DataType valueType;

	/** 查询的字段值 */
	private String value;

	public SearchData() {}

	public SearchData(String name, SearchType type, DataType valueType, String value) {
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
		return "Search [name=" + name + ", type=" + type + ", valueType=" + valueType + ", value=" + value + "]";
	}

}
