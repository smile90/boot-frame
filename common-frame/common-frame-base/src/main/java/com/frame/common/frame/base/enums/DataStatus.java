package com.frame.common.frame.base.enums;


/**
 * 数据状态
 * @author duancq
 * 2014-11-1 上午8:35:55
 */
public enum DataStatus {

	NORMAL("NORMAL", "正常"),
	DISABLE("DISABLE", "禁用"),
	DELETE("DELETE", "删除");

	private String name;
	private String text;
	private DataStatus(String name, String text) {
		this.name = name;
		this.text = text;
	}
	private DataStatus(String name) {
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