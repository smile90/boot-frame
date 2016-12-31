package com.frame.common.frame.base.params;

import java.io.Serializable;

import com.frame.common.frame.base.utils.ServerIdentityUtil;

/**
 * 请求bean
 *
 * @author duancq 2015年4月4日下午1:46:15
 */
public class RequestParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 系统标识 */
	private String systemCode;

	/** 业务来源 */
	private String serviceSource;

	/** 操作人 */
	private String operator;

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getServerMsg() {
		return ServerIdentityUtil.getLocalHostIP() + "|"
				+ ServerIdentityUtil.getLocalHostName();
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("RequestBean{");
		sb.append("systemCode='").append(systemCode).append('\'');
		sb.append(", serviceSource='").append(serviceSource).append('\'');
		sb.append(", operator='").append(operator).append('\'');
		sb.append(", serverMsg='").append(getServerMsg()).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
