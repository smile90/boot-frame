package com.frame.boot.frame.security.exception;

import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.common.frame.base.exception.BaseRuntimeException;


public class SystemException extends BaseRuntimeException {

    public static final String SYSTEM_ERROR_CODE = SysConstants.SYSTEM_CODE + "99999999";
    public static final String SYSTEM_ERROR_MSG = "system error";
    public static final String SYSTEM_ERROR_SHOW_MSG = "系统异常！";

    public SystemException() {
        super(SYSTEM_ERROR_CODE, SYSTEM_ERROR_MSG, SYSTEM_ERROR_SHOW_MSG);
    }

    public SystemException(String msg) {
        super(SYSTEM_ERROR_CODE, msg, SYSTEM_ERROR_SHOW_MSG);
    }

    public SystemException(Throwable cause) {
        super(SYSTEM_ERROR_CODE, SYSTEM_ERROR_MSG, SYSTEM_ERROR_SHOW_MSG, cause);
    }

    public SystemException(String msg, Throwable cause) {
        super(SYSTEM_ERROR_CODE, msg, SYSTEM_ERROR_SHOW_MSG, cause);
    }
}
