package com.frame.boot.frame.mybatis.util;

import com.frame.common.frame.base.bean.PageBean;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

public class PageUtil {

    public static <T> PageBean<T> page(PageList<T> pageList) {
        PageBean<T> page = new PageBean<>();
        if (pageList == null) {
            return page;
        }
        Paginator paginator = pageList.getPaginator();
        if (paginator != null) {
            page.setCurrentPage(pageList.getPaginator().getPage());
            page.setPageSize(pageList.getPaginator().getLimit());
            page.setResult(pageList);
            page.setTotalResult(pageList.getPaginator().getTotalCount());
        }
        return page;
    }
}
