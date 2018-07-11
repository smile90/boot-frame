package com.frame.boot.frame.security.exception;

import com.frame.common.frame.base.exception.BaseRuntimeException;

public class SystemSecurityException extends BaseRuntimeException {

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
