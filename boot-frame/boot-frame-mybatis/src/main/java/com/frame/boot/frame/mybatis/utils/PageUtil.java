package com.frame.boot.frame.mybatis.utils;


import com.github.pagehelper.Page;
import com.frame.common.frame.base.bean.PageBean;

import java.util.List;


public class PageUtil {
	
	private PageUtil() {}
	
	private static PageBean<List<?>> convert(Page<?> page) {
		PageBean<List<?>> pageBean = new PageBean<List<?>>();
		pageBean.setResult(page.getResult());
		pageBean.setCurrentPage(page.getPageNum());
		pageBean.setPageSize(page.getPageSize());
		pageBean.setTotalResult(page.getTotal());
		return pageBean;
	}
}