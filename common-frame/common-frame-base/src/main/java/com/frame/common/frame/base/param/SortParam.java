package com.frame.common.frame.base.param;

import com.frame.common.frame.base.enums.SortType;

import java.io.Serializable;

/**
 * 排序数据
 * @author duancq
 * 2016年8月22日 上午8:18:18
 */
public class SortParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 排序字段 */
	private String sortField;

	/** 排序方式 */
	private SortType sortType;

	/**
	 * 默认构造
	 */
	public SortParam() {}

	/**
	 * 正序
	 * @param sortField
	 */
	public SortParam(String sortField) {
		this.sortField = sortField;
		this.sortType = SortType.ASC;
	}

	/**
	 * 排序参数
	 * @param sortField
	 * @param sortType
	 */
	public SortParam(String sortField, SortType sortType) {
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

}
