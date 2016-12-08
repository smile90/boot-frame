package com.frame.common.frame.base.enums;

/**
 * 性别
 * @author duancq
 * 2014-11-1 上午8:37:55
 */
public enum Sex {

	MAN("MAN", "男"),
	WOMAN("WOMAN", "女"),
	UNKNOWN("UNKNOWN", "未知"),
	OTHER("OTHER", "其他");

	private String name;
	private String text;
	private Sex(String name, String text) {
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
