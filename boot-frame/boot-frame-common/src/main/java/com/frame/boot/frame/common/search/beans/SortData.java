package com.frame.boot.frame.common.search.beans;

import com.frame.boot.frame.common.search.enums.SortType;

/**
 * 排序数据
 * @author duancq
 * 2016年8月22日 上午8:18:18
 */
public class SortData {

	/** 排序字段 */
	private String sortField;

	/** 排序方式 */
	private SortType sortType;

	public SortData() {}

	public SortData(String sortField, SortType sortType) {
		this.sortField = sortField;
		this.sortType = sortType;
	}

	/**
	 * 正序
	 * @param sortField
	 * @return
	 */
	public static SortData asc(String sortField) {
		return new SortData(sortField, SortType.ASC);
	}

	/**
	 * 倒序
	 * @param sortField
	 * @return
	 */
	public static SortData desc(String sortField) {
		return new SortData(sortField, SortType.DESC);
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
		return String.format("SortData [sortField=%s, sortType=%s]", sortField, sortType);
	}

}
