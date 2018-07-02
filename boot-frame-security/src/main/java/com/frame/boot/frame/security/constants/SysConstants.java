package com.frame.boot.frame.security.constants;

/**
 * 系统常量
 */
public class SysConstants {

    public static final String SYSTEM_NAME = "boot-frame-security";
    public static final String SYSTEM_CODE = "0001";

    /** 公共 */
    public static final String PUBLIC_SYSTEM_CODE_COMMON = SYSTEM_CODE + "0001";
    /** 用户未登录 */
    public static final String COMMON_SYSTEM_ERROR_CODE = PUBLIC_SYSTEM_CODE_COMMON + "9998";
    public static final String COMMON_SYSTEM_ERROR_MSG = "system is error.";
    public static final String COMMON_SYSTEM_SHOW_MSG = "系统异常，请重试或联系管理员";

    /** 权限 */
    public static final String PUBLIC_SYSTEM_CODE_AUTH = SYSTEM_CODE + "0002";
    /** 权限系统异常 */
    public static final String AUTH_ERROR_CODE = PUBLIC_SYSTEM_CODE_AUTH + "9999";
    public static final String AUTH_ERROR_MSG = "auth is error.";
    public static final String AUTH_ERROR_SHOW_MSG = "权限验证异常";

    /** 用户权限异常 */
    public static final String USER_AUTH_ERROR_CODE = PUBLIC_SYSTEM_CODE_AUTH + "9998";
    public static final String USER_AUTH_ERROR_MSG = "user auth is error.";
    public static final String USER_AUTH_ERROR_SHOW_MSG = "用户权限异常";

    /** SUPER_ADMIN */
    public static final String ROLE_CODE_SUPER_ADMIN = "SUPER_ADMIN";

    /** ROOT */
    public static final String MODULE_CODE_ROOT = "ROOT";
}
