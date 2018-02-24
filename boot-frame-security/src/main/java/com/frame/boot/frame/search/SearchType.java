package com.frame.boot.frame.search;

/**
 * 查询类别
 * @author duancq
 * 2016年8月26日 下午5:45:06
 */
public enum SearchType {

	LIKE("LIKE", "相似"),
	EQ("EQ", "相等"),
	NE("NE", "不等"),
	GE("GE", "大于等于"),
	GT("GT", "大于"),
	LE("LE", "小于等于"),
	LT("LT", "小于"),
	IN("IN", "包含");

	private String name;
	private String text;
	private SearchType(String name, String text) {
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
