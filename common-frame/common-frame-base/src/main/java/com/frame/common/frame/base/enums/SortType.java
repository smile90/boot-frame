package com.frame.common.frame.base.enums;


/**
 * 排序类别
 * @author duancq
 * 2014-11-1 上午8:35:55
 */
public enum SortType {

	ASC("ASC", "正序"),
	DESC("DESC", "倒序");

	private String name;
	private String text;
	private SortType(String name, String text) {
		this.name = name;
		this.text = text;
	}
	private SortType(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


}