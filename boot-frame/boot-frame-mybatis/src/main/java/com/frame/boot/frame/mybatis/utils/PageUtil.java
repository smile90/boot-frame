package com.frame.boot.frame.mybatis.utils;


import com.baomidou.mybatisplus.plugins.Page;
import com.frame.common.frame.base.bean.PageBean;

import java.util.List;


public class PageUtil {
	
	private PageUtil() {}
	
	private static <T> PageBean<T> convert(Page<T> page) {
		PageBean<T> pageBean = new PageBean<>();
		pageBean.setResult(page.getRecords());
		pageBean.setCurrentPage(page.getCurrent());
		pageBean.setPageSize(page.getSize());
		pageBean.setTotalResult(page.getTotal());
		return pageBean;
	}
}