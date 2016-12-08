package com.frame.common.frame.base.enums;

/**
 * TRUE/FALSE枚举
 * @author duancq
 * 2014-11-29 上午9:32:34
 */
public enum TrueFalse {

	TRUE("TRUE", "是"),
	FALSE("FALSE", "否");

	private String name;
	private String text;
	private TrueFalse(String name, String text) {
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
