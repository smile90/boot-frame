package com.frame.common.frame.base.exception;

/**
 * 基础异常类
 * @author duancq
 * 2015年8月10日 上午9:13:54
 */
public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6660409356476597628L;

	/** 错误码 */
	private String errorCode;

	/** 显示内容（提示用户） */
	private String showMsg;

	public BaseRuntimeException(String errorCode, String message, String showMsg) {
		super(message);
		this.errorCode = errorCode;
		this.showMsg = showMsg;
	}

	public BaseRuntimeException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
		if (cause instanceof BaseRuntimeException) {
			this.setErrorCode(((BaseRuntimeException) cause).getErrorCode());
			this.setShowMsg(((BaseRuntimeException) cause).getShowMsg());
		}
	}

	public BaseRuntimeException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(String errorCode, String message, String showMsg, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.showMsg = showMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getShowMsg() {
		return showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}

	@Override
	public String getMessage() {
		return errorCode + "/" + super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return errorCode + "/" + super.getLocalizedMessage();
	}

}
