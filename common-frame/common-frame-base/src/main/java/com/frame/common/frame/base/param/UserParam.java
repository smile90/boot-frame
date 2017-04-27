package com.frame.common.frame.base.param;

import java.io.Serializable;

public class UserParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userRole;
	private String userNo;
	
	public UserParam() {
	}
	
	public UserParam(String userRole, String userNo) {
		this.userRole = userRole;
		this.userNo = userNo;
	}
	
	public String getUserRole() {
		return userRole;
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
}
