package com.frame.boot.frame.security.exception;

import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.common.frame.base.exception.BaseRuntimeException;

/**
 * 权限异常
 */
public class SecurityException extends BaseRuntimeException {

    public static final String USERNAME_PWD_NOT_SUCC_CODE = SysConstants.PUBLIC_SYSTEM_CODE_ACC + "0001";
    public static final String USERNAME_PWD_NOT_SUCC_MSG = "用户名或密码错误！";
    public static final String USER_ERROR_CODE = SysConstants.PUBLIC_SYSTEM_CODE_ACC + "0002";
    public static final String USER_ERROR_MSG = "用户状态异常，请联系管理员！";
    public static final String USER_LOCKED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_ACC + "0003";
    public static final String USER_LOCKED_MSG = "用户状态异常，被锁定！";
    public static final String USER_DISABLED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_ACC + "0004";
    public static final String USER_DISABLED_MSG = "用户状态异常，被禁用！";
    public static final String USER_EXPIRED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_ACC + "0005";
    public static final String USER_EXPIRED_MSG = "用户状态异常，已过期！";

    public SecurityException(String errorCode, String message, String showMsg) {
        super(errorCode, message, showMsg);
    }

    public SecurityException(String errorCode, String message) {
        super(errorCode, message);
    }

    public SecurityException(Throwable cause) {
        super(cause);
    }

    public SecurityException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SecurityException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public SecurityException(String errorCode, String message, String showMsg, Throwable cause) {
        super(errorCode, message, showMsg, cause);
    }
}
