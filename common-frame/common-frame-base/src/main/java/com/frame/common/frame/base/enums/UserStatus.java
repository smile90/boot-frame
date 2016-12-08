package com.frame.common.frame.base.enums;

/**
 * 用户状态
 * @author duancq
 * 2014-11-1 上午8:40:36
 */
public enum UserStatus {

	NORMAL("NORMAL", "正常"),
	UNREVIEW("UNREVIEW", "未审核"),
	REVIEWING("REVIEWING", "审核中"),
	UNAPPROVED("UNAPPROVED", "未审核通过"),
	LOCKED("LOCKED", "锁定"),
	DISABLED("DISABLED", "禁用"),
	EXPIRED("EXPIRED", "失效（过期）"),
	DELETED("DELETED", "删除");

	private String name;
	private String text;
	private UserStatus(String name, String text) {
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
