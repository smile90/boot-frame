package com.frame.common.frame.base.bean;

import com.frame.common.frame.base.enums.SuccessFail;
import com.frame.common.frame.base.utils.ServerIdentityUtil;

/**
 * 响应bean
 * @author duancq
 * 2014-1-1 上午11:49:21
 */
public class ResponseBean<T> extends InfoBean<T> {

	private static final long serialVersionUID = 1L;

	private String serverMsg = ServerIdentityUtil.getLocalHostIP() + "|" + ServerIdentityUtil.getLocalHostName();

	public ResponseBean() {}

	public ResponseBean(SuccessFail successFail, String showMsg, String code, String description, T object) {
		super(successFail, showMsg, code, description, object);
	}

	public ResponseBean(SuccessFail successFail) {
		super(successFail);
	}

	public ResponseBean(SuccessFail successFail, String showMsg) {
		super(successFail, showMsg);
	}

	public ResponseBean(SuccessFail successFail, T object) {
		super(successFail, object);
	}

	public ResponseBean(SuccessFail successFail, String showMsg, T object) {
		super(successFail, showMsg, object);
	}

	public ResponseBean(SuccessFail successFail, String showMsg, String code, String description) {
		super(successFail, showMsg, code, description);
	}

	/**
	 * 成功
	 * @return
	 */
	public static <T> ResponseBean<T> SUCCESS() {
		return new ResponseBean<T>(SuccessFail.SUCCESS);
	}

	/**
	 * 成功内容
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> SUCCESS(T object) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, object);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @return
	 */
	public static <T> ResponseBean<T> SUCCESS(String showMsg) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, showMsg);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> SUCCESS(String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, showMsg, object);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @return
	 */
	public static <T> ResponseBean<T> SUCCESS(String showMsg, String code, String description) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, showMsg, code, description);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> SUCCESS(String showMsg, String code, String description, T object) {
		return new ResponseBean<T>(SuccessFail.SUCCESS, showMsg, code, description, object);
	}

	/**
	 * 失败
	 * @return
	 */
	public static <T> ResponseBean<T> FAIL() {
		return new ResponseBean<T>(SuccessFail.FAIL);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @return
	 */
	public static <T> ResponseBean<T> FAIL(String showMsg) {
		return new ResponseBean<T>(SuccessFail.FAIL, showMsg);
	}

	/**
	 * 失败内容
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> FAIL(T object) {
		return new ResponseBean<T>(SuccessFail.FAIL, object);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> FAIL(String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.FAIL, showMsg, object);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @return
	 */
	public static <T> ResponseBean<T> FAIL(String showMsg, String code, String description) {
		return new ResponseBean<T>(SuccessFail.FAIL, showMsg, code, description);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> FAIL(String showMsg, String code, String description, T object) {
		return new ResponseBean<T>(SuccessFail.FAIL, showMsg, code, description, object);
	}

	/**
	 * 异常
	 * @return
	 */
	public static <T> ResponseBean<T> EXCEPTION() {
		return new ResponseBean<T>(SuccessFail.EXCEPTION);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @return
	 */
	public static <T> ResponseBean<T> EXCEPTION(String showMsg) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, showMsg);
	}

	/**
	 * 异常内容
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> EXCEPTION(T object) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, object);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> EXCEPTION(String showMsg, T object) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, showMsg, object);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @return
	 */
	public static <T> ResponseBean<T> EXCEPTION(String showMsg, String code, String description) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, showMsg, code, description);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @param object
	 * @return
	 */
	public static <T> ResponseBean<T> EXCEPTION(String showMsg, String code, String description, T object) {
		return new ResponseBean<T>(SuccessFail.EXCEPTION, showMsg, code, description, object);
	}

	/**
	 * 获取服务器
	 * @return
	 */
	public String getServerMsg() {
		return this.serverMsg;
	}

}
