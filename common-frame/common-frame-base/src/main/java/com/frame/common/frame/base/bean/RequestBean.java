package com.frame.common.frame.base.bean;

import java.io.Serializable;

import com.frame.common.frame.base.utils.ServerIdentityUtil;

/**
 * 请求bean
 *
 * @author duancq 2015年4月4日下午1:46:15
 */
public class RequestBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getServerMsg() {
		return ServerIdentityUtil.getLocalHostIP() + "|"
				+ ServerIdentityUtil.getLocalHostName();
	}
}
