package com.frame.boot.frame.security.exception;

import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.common.frame.base.exception.BaseRuntimeException;

public class SystemSecurityException extends BaseRuntimeException {

    public static final String USERNAME_PWD_ERROR_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0001";
    public static final String USERNAME_PWD_ERROR_MSG = "用户名或密码错误";

    public static final String VALID_CODE_ERROR_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0002";
    public static final String VALID_CODE_ERROR_MSG = "验证码错误";

    public static final String USER_ERROR_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0003";
    public static final String USER_ERROR_MSG = "用户状态异常，请联系管理员";
    public static final String USER_LOCKED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0004";
    public static final String USER_LOCKED_MSG = "用户状态异常，被锁定";
    public static final String USER_DISABLED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0005";
    public static final String USER_DISABLED_MSG = "用户状态异常，被禁用";
    public static final String USER_EXPIRED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0006";
    public static final String USER_EXPIRED_MSG = "用户状态异常，已过期";

    public SystemSecurityException(String errorCode, String message, String showMsg) {
        super(errorCode, message, showMsg);
    }

    public SystemSecurityException(String errorCode, String message) {
        super(errorCode, message);
    }

    public SystemSecurityException(Throwable cause) {
        super(cause);
    }

    public SystemSecurityException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SystemSecurityException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public SystemSecurityException(String errorCode, String message, String showMsg, Throwable cause) {
        super(errorCode, message, showMsg, cause);
    }

}
