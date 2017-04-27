package com.frame.common.frame.base.enums;

/**
 * YES/NO枚举
 * @author duancq
 * 2014-11-29 上午9:32:52
 */
public enum YesNo {

	YES("YES", "是"),
	NO("NO", "否"),
	Y("Y", "是"),
	N("N", "否");

	private String name;
	private String text;
	private YesNo(String name, String text) {
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
