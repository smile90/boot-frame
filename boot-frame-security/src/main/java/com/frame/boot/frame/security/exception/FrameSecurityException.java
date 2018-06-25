package com.frame.boot.frame.security.exception;

import com.frame.boot.frame.security.constants.SysConstants;
import org.springframework.security.core.AuthenticationException;

public class FrameSecurityException extends AuthenticationException {

    public static final String USERNAME_PWD_ERROR_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0001";
    public static final String USERNAME_PWD_ERROR_MSG = "用户名或密码错误！";

    public static final String VALID_CODE_ERROR_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0002";
    public static final String VALID_CODE_ERROR_MSG = "验证码错误";

    public static final String USER_ERROR_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0003";
    public static final String USER_ERROR_MSG = "用户状态异常，请联系管理员！";
    public static final String USER_LOCKED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0004";
    public static final String USER_LOCKED_MSG = "用户状态异常，被锁定！";
    public static final String USER_DISABLED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0005";
    public static final String USER_DISABLED_MSG = "用户状态异常，被禁用！";
    public static final String USER_EXPIRED_CODE = SysConstants.PUBLIC_SYSTEM_CODE_AUTH + "0006";
    public static final String USER_EXPIRED_MSG = "用户状态异常，已过期！";

    /** 错误码 */
    private String errorCode;
    /** 显示内容（提示用户） */
    private String showMsg;

    public FrameSecurityException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public FrameSecurityException(String errorCode, String message, String showMsg) {
        super(message);
        this.errorCode = errorCode;
        this.showMsg = showMsg;
    }

    public FrameSecurityException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public FrameSecurityException(String errorCode, String message, String showMsg, Throwable cause) {
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

}
