package com.frame.boot.frame.search;

import com.frame.common.frame.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 查询数据
 * @author duancq
 * 2016年8月27日 下午1:19:43
 */
public class SearchData {

    private Logger logger = LoggerFactory.getLogger(getClass());

	/** 查询的字段名称 */
	private String name;

	/** 查询条件的类别 */
	private SearchType type;

	/** 查询的字段类别 */
	private ValueType valueType;

	/** 查询的字段值 */
	private String value;

	public SearchData() {}

	public SearchData(String name, SearchType type, ValueType valueType, String value) {
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

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

	public String getValue() {
		return value;
	}

	public Object getRealValue() {
		try {
            switch (valueType) {
                case INTEGER:
                    return Integer.valueOf(value);
                case LONG:
                    return Long.valueOf(value);
                case FLOAT:
                    return Float.valueOf(value);
                case DOUBLE:
                    return Double.valueOf(value);
                case STRING:
                    return value;
                case DATE:
                    return DateUtil.parseDate(value, DateUtil.FORMAT_yyyy_MM_dd);
                case DATETIME:
                    return DateUtil.parseDate(value, DateUtil.FORMAT_yyyy_MM_dd_HH_mm_ss);
                default:
                    return value;
            }
        } catch (Exception e) {
            logger.error(null, e);
            return value;
        }
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Search [name=" + name + ", type=" + type + ", valueType=" + valueType + ", value=" + value + "]";
	}

}
