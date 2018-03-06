package com.frame.boot.frame.mybatis.search.exception;


import com.frame.common.frame.base.constants.ExceptionConstant;
import com.frame.common.frame.base.exception.BaseRuntimeException;

/**
 * 查询异常
 * @author duancq
 * 2013-11-16 下午11:28:58
 */
public class SearchException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String BUILD_SEARCH_ERROR_CODE = ExceptionConstant.PUBLIC_SYSTEM_CODE_SEARCH + "0001";
	public static final String BUILD_SEARCH_ERROR_MSG = "构建查询错误！";

	public SearchException(String errorCode, String message, String showMsg, Throwable cause) {
		super(errorCode, message, showMsg, cause);
	}

	public SearchException(String errorCode, String message, String showMsg) {
		super(errorCode, message, showMsg);
	}

	public SearchException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public SearchException(String errorCode, String message) {
		super(errorCode, message);
	}

	public SearchException(Throwable cause) {
		super(cause);
	}

	public SearchException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
