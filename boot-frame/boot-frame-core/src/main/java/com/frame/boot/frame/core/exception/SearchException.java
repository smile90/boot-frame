package com.frame.boot.frame.core.exception;

import com.frame.boot.frame.core.constants.BootFrameCoreConstants;
import com.frame.common.frame.base.exception.BaseRuntimeException;

public class SearchException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String ERROR_CODE_CRITERIA_ERROR = BootFrameCoreConstants.PUBLIC_MODUL_CODE_SEARCH + "9999";
	public static final String SHOW_MSG_CRITERIA_ERROR = "构建查询条件出错，请联系管理员。";

	public static final String ERROR_CODE_CONVERT_SEARCH_VALUE_ERROR = BootFrameCoreConstants.PUBLIC_MODUL_CODE_SEARCH + "9998";
	public static final String SHOW_MSG_CONVERT_SEARCH_VALUE_ERROR = "查询值转换出错，请联系管理员。";

	public SearchException(BaseRuntimeException cause) {
		super(cause);
	}

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

	public SearchException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public SearchException(Throwable cause) {
		super(cause);
	}

}
