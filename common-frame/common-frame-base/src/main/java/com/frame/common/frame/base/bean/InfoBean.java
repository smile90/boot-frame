package com.frame.common.frame.base.bean;

import com.frame.common.frame.base.enums.SuccessFail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 信息bean
 * @author duancq
 * 2014-1-1 上午11:49:21
 */
public class InfoBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CODE = "code";
	private static final String MSG = "msg";
	private static final String OBJECT = "object";
	private static final String DESCRIPTION = "description";

	/** 状态 */
	private SuccessFail successFail;

	/** 相关信息 */
	private Map<String, Object> msg = new HashMap<String, Object>();

	/**
	 * 默认构造函数
	 */
	public InfoBean() {}

	/**
	 * 构造函数
	 * @param successFail
	 */
	protected InfoBean(SuccessFail successFail) {
		this.successFail = successFail;
	}

	/**
	 * 构造函数
	 * @param successFail
	 * @param showMsg
	 */
	protected InfoBean(SuccessFail successFail, String showMsg) {
		this.successFail = successFail;
		this.msg.put(MSG, showMsg);
	}

	/**
	 * 构造函数
	 * @param successFail
	 * @param object
	 */
	protected InfoBean(SuccessFail successFail, T object) {
		this.successFail = successFail;
		this.msg.put(OBJECT, object);
	}

	/**
	 * 构造函数
	 * @param successFail
	 * @param showMsg
	 * @param object
	 */
	protected InfoBean(SuccessFail successFail, String showMsg, T object) {
		this.successFail = successFail;
		this.msg.put(MSG, showMsg);
		this.msg.put(OBJECT, object);
	}

	/**
	 * 构造函数
	 * @param successFail
	 * @param showMsg
	 * @param code
	 * @param description
	 */
	protected InfoBean(SuccessFail successFail, String showMsg, String code, String description) {
		this.successFail = successFail;
		this.msg.put(MSG, showMsg);
		this.msg.put(CODE, code);
		this.msg.put(DESCRIPTION, description);
	}

	/**
	 * 构造函数
	 * @param successFail
	 * @param showMsg
	 * @param code
	 * @param description
	 * @param object
	 */
	protected InfoBean(SuccessFail successFail, String showMsg, String code, String description, T object) {
		this.successFail = successFail;
		this.msg.put(MSG, showMsg);
		this.msg.put(CODE, code);
		this.msg.put(DESCRIPTION, description);
		this.msg.put(OBJECT, object);
	}

	/**
	 * 成功
	 * @return
	 */
	public static <T> InfoBean<T> SUCCESS() {
		return new InfoBean<T>(SuccessFail.SUCCESS);
	}

	/**
	 * 成功内容
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> SUCCESS(T object) {
		return new InfoBean<T>(SuccessFail.SUCCESS, object);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @return
	 */
	public static <T> InfoBean<T> SUCCESS(String showMsg) {
		return new InfoBean<T>(SuccessFail.SUCCESS, showMsg);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> SUCCESS(String showMsg, T object) {
		return new InfoBean<T>(SuccessFail.SUCCESS, showMsg, object);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @return
	 */
	public static <T> InfoBean<T> SUCCESS(String showMsg, String code, String description) {
		return new InfoBean<T>(SuccessFail.SUCCESS, showMsg, code, description);
	}

	/**
	 * 成功内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> SUCCESS(String showMsg, String code, String description, T object) {
		return new InfoBean<T>(SuccessFail.SUCCESS, showMsg, code, description, object);
	}

	/**
	 * 失败
	 * @return
	 */
	public static <T> InfoBean<T> FAIL() {
		return new InfoBean<T>(SuccessFail.FAIL);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @return
	 */
	public static <T> InfoBean<T> FAIL(String showMsg) {
		return new InfoBean<T>(SuccessFail.FAIL, showMsg);
	}

	/**
	 * 失败内容
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> FAIL(T object) {
		return new InfoBean<T>(SuccessFail.FAIL, object);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> FAIL(String showMsg, T object) {
		return new InfoBean<T>(SuccessFail.FAIL, showMsg, object);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @return
	 */
	public static <T> InfoBean<T> FAIL(String showMsg, String code, String description) {
		return new InfoBean<T>(SuccessFail.FAIL, showMsg, code, description);
	}

	/**
	 * 失败内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> FAIL(String showMsg, String code, String description, T object) {
		return new InfoBean<T>(SuccessFail.FAIL, showMsg, code, description, object);
	}

	/**
	 * 异常
	 * @return
	 */
	public static <T> InfoBean<T> EXCEPTION() {
		return new InfoBean<T>(SuccessFail.EXCEPTION);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @return
	 */
	public static <T> InfoBean<T> EXCEPTION(String showMsg) {
		return new InfoBean<T>(SuccessFail.EXCEPTION, showMsg);
	}

	/**
	 * 异常内容
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> EXCEPTION(T object) {
		return new InfoBean<T>(SuccessFail.EXCEPTION, object);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> EXCEPTION(String showMsg, T object) {
		return new InfoBean<T>(SuccessFail.EXCEPTION, showMsg, object);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @return
	 */
	public static <T> InfoBean<T> EXCEPTION(String showMsg, String code, String description) {
		return new InfoBean<T>(SuccessFail.EXCEPTION, showMsg, code, description);
	}

	/**
	 * 异常内容
	 * @param showMsg
	 * @param code
	 * @param description
	 * @param object
	 * @return
	 */
	public static <T> InfoBean<T> EXCEPTION(String showMsg, String code, String description, T object) {
		return new InfoBean<T>(SuccessFail.EXCEPTION, showMsg, code, description, object);
	}

	/**
	 * 放置信息
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		this.msg.put(key, value);
	}

	/**
	 * 获取信息
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return msg.get(key);
	}

	/**
	 * 设置结果
	 * @param successFail
	 */
	public void setResult(SuccessFail successFail) {
		this.successFail = successFail;
	}

	/**
	 * 获取结果
	 * @return
	 */
	public SuccessFail getResult() {
		return successFail;
	}

	/**
	 * 获取显示信息
	 * @return
	 */
	public String getShowMsg() {
		return successFail == null ? null : ((String) msg.get(MSG));
	}

	/**
	 * 设置标识
	 * @param code
	 */
	public void setCode(String code) {
		put(CODE, code);
	}

	/**
	 * 获取标识
	 * @return
	 */
	public String getCode() {
		return (String) get(CODE);
	}

	/**
	 * 设置备注
	 * @param description
	 */
	public void setDescription(String description) {
		put(DESCRIPTION, description);
	}

	/**
	 * 获取备注
	 * @return
	 */
	public String getDescription() {
		return (String) get(DESCRIPTION);
	}


	/**
	 * 设置信息对象
	 * @param msg
	 */
//	public void setMsg(Map<String, Object> msg) {
//		this.msg = msg;
//	}

	/**
	 * 获取信息对象
	 * @return
	 */
//	public Map<String, Object> getMsg() {
//		return msg;
//	}

	/**
	 * 设置对象
	 * @param object
	 */
	public void setObject(T object) {
		put(OBJECT, object);
	}

	/**
	 * 获取对象
	 * @return
	 */
	public T getObject() {
		return (T) get(OBJECT);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("InfoBean{");
		sb.append("successFail=").append(successFail);
		sb.append(", msg=").append(msg);
		sb.append('}');
		return sb.toString();
	}
}
