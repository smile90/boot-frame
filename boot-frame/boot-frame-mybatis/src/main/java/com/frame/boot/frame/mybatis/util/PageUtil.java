package com.frame.boot.frame.mybatis.util;

import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.common.frame.base.bean.PageBean;
import com.frame.boot.frame.mybatis.bean.PageList;
import com.frame.boot.frame.mybatis.bean.Paginator;
import com.frame.common.frame.base.param.PageParam;

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

    public static PageBounds paginator(PageParam pageParam) {
        PageParam result = pageParam;
        if (result == null) {
            result = new PageParam();
        }
        return new PageBounds(result.getPage(), result.getRows());
    }
}
