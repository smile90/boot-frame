package com.frame.common.frame.base.params;

import com.frame.common.frame.base.bean.RequestBean;

public class PageParam extends RequestBean {

    private static final long serialVersionUID = 1L;

    private int page = 1;
    private int rows = 15;

    public PageParam() {
    }

    public PageParam(int page) {
        this.page = page;
    }

    public PageParam(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    /**
     * JPA默认从0页开始
     *
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
