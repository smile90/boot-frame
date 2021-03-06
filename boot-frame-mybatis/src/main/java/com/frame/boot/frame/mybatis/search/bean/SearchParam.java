package com.frame.boot.frame.mybatis.search.bean;

import java.util.List;

/**
 * 查询参数
 * @author duancq
 * 2016年8月26日 下午5:58:35
 */
public class SearchParam {

	/** 搜索条件 */
	private List<SearchData> searchs;

	public List<SearchData> getSearchs() {
		return searchs;
	}

	public void setSearchs(List<SearchData> searchs) {
		this.searchs = searchs;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("SearchParam{");
		sb.append("searchs=").append(searchs);
		sb.append('}');
		return sb.toString();
	}
}
