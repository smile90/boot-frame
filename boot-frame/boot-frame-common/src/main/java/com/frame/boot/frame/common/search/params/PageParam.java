package com.frame.boot.frame.common.search.params;

/**
 * 分页参数
 * @author duancq
 * 2016年8月22日 上午8:18:18
 */
public class PageParam {

	private int page = 1;
	private int rows = 15;

	/**
	 * JPA默认从0页开始
	 * @return
	 */
	public int getJpaPage() {
		return page > 0 ? page - 1 : page;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageParam [page=" + page + ", rows=" + rows + "]";
	}

}
