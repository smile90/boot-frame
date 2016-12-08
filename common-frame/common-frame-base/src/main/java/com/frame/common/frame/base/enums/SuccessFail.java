package com.frame.common.frame.base.enums;

/**
 * 成功/失败枚举
 * @author duancq
 * 2014-11-29 上午9:34:39
 */
public enum SuccessFail {

	SUCCESS("SUCCESS", "成功"),
	FAIL("FAIL", "失败"),
	EXCEPTION("EXCEPTION", "异常"),
	ERROR("ERROR", "错误");

	private String name;
	private String text;
	private SuccessFail(String name, String text) {
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
