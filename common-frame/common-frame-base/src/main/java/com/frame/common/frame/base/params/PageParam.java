package com.frame.common.frame.base.params;

public class PageParam extends RequestParam {

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
        final StringBuffer sb = new StringBuffer("PageParam{");
        sb.append("page=").append(page);
        sb.append(", rows=").append(rows);
        sb.append('}');
        return sb.toString();
    }
}
