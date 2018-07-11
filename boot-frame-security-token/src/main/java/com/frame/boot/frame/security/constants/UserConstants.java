package com.frame.boot.frame.security.constants;

/**
 * 系统常量
 */
public class UserConstants {

    /** 用户 */
    public static final String USER = SysConstants.SYSTEM_CODE + "0004";
    /** 用户相关异常 */
    public static final String USER_ERROR_CODE = USER + "9999";
    public static final String USER_ERROR_MSG = "user is error.";
    public static final String USER_ERROR_SHOW_MSG = "用户管理异常";
    /** 用户标识已存在 */
    public static final String USER_EXIST_ERROR_CODE = USER + "0001";
}
