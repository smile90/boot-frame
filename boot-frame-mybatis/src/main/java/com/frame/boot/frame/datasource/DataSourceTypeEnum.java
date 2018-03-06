package com.frame.boot.frame.datasource;

public enum DataSourceTypeEnum {

    PRIMARY("primary", "主数据源"), READ("read", "只读数据源");

    private String name;
    private String text;
    private DataSourceTypeEnum(String name, String text) {
        this.name = name;
        this.text = text;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
