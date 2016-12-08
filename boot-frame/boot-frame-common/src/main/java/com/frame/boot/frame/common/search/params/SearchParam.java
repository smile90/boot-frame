package com.frame.boot.frame.common.search.params;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.frame.boot.frame.common.search.beans.SearchData;
import com.frame.boot.frame.utils.EmptyUtil;

/**
 * 查询参数
 * @author duancq
 * 2016年8月26日 下午5:58:35
 */
public class SearchParam {

	/** 搜索条件 */
	private List<SearchData> searchs;

	/** 搜索条件（JSON格式） */
	private String searchJson;

	public String getSearchJson() {
		return searchJson;
	}

	public void setSearchJson(String searchJson) {
		this.searchJson = searchJson;
	}

	public List<SearchData> getSearch() {
		if (EmptyUtil.notEmpty(searchJson)) {
			searchs = JSON.parseArray(searchJson, SearchData.class);
		}
		return searchs;
	}

	@Override
	public String toString() {
		return String.format("SearchParam [searchJson=%s]", searchJson);
	}

}
