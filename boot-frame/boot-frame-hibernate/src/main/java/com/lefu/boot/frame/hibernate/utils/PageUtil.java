package com.frame..boot.frame.hibernate.utils;

import com.frame..common.frame.base.bean.PageBean;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageUtil {

	/**
	 * 转换对象
	 * @param page
	 * @param <T>
	 * @return
	 */
	public static <T> PageBean<List<T>> convert(Page<T> page) {
		PageBean<List<T>> pageBean = new PageBean<List<T>>();
		pageBean.setResult(page.getContent());
		pageBean.setCurrentPage(page.getNumber() >= 0 ? page.getNumber() + 1 : page.getNumber());
		pageBean.setPageSize(page.getSize());
		pageBean.setTotalResult(page.getTotalElements());
		return pageBean;
	}
}