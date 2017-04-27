package com.frame.common.frame.base.bean;

import java.io.Serializable;

public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageSize = 10;
	private long totalPage;
	private long totalResult;
	private long currentPage;
	private T result;

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalPage() {
		return pageSize == 0 ? 0 : (int)Math.ceil((double)totalResult / (double)pageSize);
	}
	public long getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(long totalResult) {
		this.totalResult = totalResult;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}

}
