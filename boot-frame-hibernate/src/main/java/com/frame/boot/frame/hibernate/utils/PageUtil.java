package com.frame.boot.frame.hibernate.utils;

import com.frame.common.frame.base.bean.PageBean;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageUtil {

	/**
	 * 转换对象
	 * @param page
	 * @param <T>
	 * @return
	 */
	public static <T> PageBean<T> convert(Page<T> page) {
		PageBean<T> pageBean = new PageBean<>();
		pageBean.setResult(page.getContent());
		pageBean.setCurrentPage(page.getNumber() >= 0 ? page.getNumber() + 1 : page.getNumber());
		pageBean.setPageSize(page.getSize());
		pageBean.setTotalResult(page.getTotalElements());
		return pageBean;
	}
}