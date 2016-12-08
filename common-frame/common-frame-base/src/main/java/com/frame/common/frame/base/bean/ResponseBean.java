package com.frame.common.frame.base.bean;

import java.io.Serializable;

import com.frame.common.frame.base.enums.SuccessFail;
import com.frame.common.frame.base.utils.ServerIdentityUtil;

/**
 * 响应bean
 * @author duancq 2015年4月4日下午1:46:15
 */
public class ResponseBean<T extends Serializable> extends InfoBean<T> {

	private static final long serialVersionUID = 1L;

	public ResponseBean() {
		super();
	}

	public ResponseBean(SuccessFail successFail, String code, String description, String showMsg, T object) {
		super(successFail, code, description, showMsg, object);
	}

	public ResponseBean(SuccessFail successFail, String code, String description, String showMsg) {
		super(successFail, code, description, showMsg);
	}

	public ResponseBean(SuccessFail successFail, String showMsg, T object) {
		super(successFail, showMsg, object);
	}

	public ResponseBean(SuccessFail successFail, String showMsg) {
		super(successFail, showMsg);
	}

	public ResponseBean(SuccessFail successFail, T object) {
		super(successFail, object);
	}

	public ResponseBean(SuccessFail successFail) {
		super(successFail);
	}

	/**
	 * 成功
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> SUCCESS() {
		return new ResponseBean<T>(SuccessFail.SUCCESS);
	}

	/**
	 * 成功内容
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> SUCCESS(T object) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, object);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> SUCCESS(String showMsg) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, showMsg);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> SUCCESS(String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, showMsg, object);
	}

	/**
	 * 成功内容
	 * @param code
	 * @param description
	 * @param showMsg
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> SUCCESS(String code, String description, String showMsg) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, code, description, showMsg);
	}

	/**
	 * 成功内容
	 * @param code
	 * @param description
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> SUCCESS(String code, String description, String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, code, description, showMsg, object);
	}

	/**
	 * 失败
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> FAIL() {
		return new ResponseBean<T>(SuccessFail.FAIL);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> FAIL(String showMsg) {
		return new ResponseBean<T>(SuccessFail.FAIL, showMsg);
	}

	/**
	 * 失败内容
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> FAIL(T object) {
		return new ResponseBean<T>(SuccessFail.FAIL, object);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> FAIL(String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.FAIL, showMsg, object);
	}

	/**
	 * 失败内容
	 * @param code
	 * @param description
	 * @param showMsg
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> FAIL(String code, String description, String showMsg) {
		return new ResponseBean<T>(SuccessFail.FAIL, code, description, showMsg);
	}

	/**
	 * 失败内容
	 * @param code
	 * @param description
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> FAIL(String code, String description, String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.FAIL, code, description, showMsg, object);
	}

	/**
	 * 异常
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> EXCEPTION() {
		return new ResponseBean<T>(SuccessFail.EXCEPTION);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> EXCEPTION(String showMsg) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, showMsg);
	}

	/**
	 * 异常内容
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> EXCEPTION(T object) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, object);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> EXCEPTION(String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, showMsg, object);
	}

	/**
	 * 异常内容
	 * @param code
	 * @param description
	 * @param showMsg
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> EXCEPTION(String code, String description, String showMsg) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, code, description, showMsg);
	}

	/**
	 * 异常内容
	 * @param code
	 * @param description
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T extends Serializable> ResponseBean<T> EXCEPTION(String code, String description, String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, code, description, showMsg, object);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ResponseBean{");
		sb.append("successFail=").append(getResult());
		sb.append(", msg=").append(getMsg());
		sb.append(". ").append(ServerIdentityUtil.getLocalHostIP() + "|" + ServerIdentityUtil.getLocalHostName());
		sb.append('}');
		return sb.toString();
	}

}
