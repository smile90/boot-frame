package com.frame.boot.frame.security.constants;

/**
 * 系统常量
 */
public class SysConstants {

    public static final String SYSTEM_NAME = "boot-frame-security";
    public static final String SYSTEM_CODE = "0001";

    /** 权限 */
    public static final String PUBLIC_SYSTEM_CODE_AUTH = SYSTEM_CODE + "0001";
    /** 权限系统异常 */
    public static final String AUTH_ERROR_CODE = PUBLIC_SYSTEM_CODE_AUTH + "9999";
    public static final String AUTH_ERROR_MSG = "auth is error.";
    public static final String AUTH_ERROR_SHOW_MSG = "权限验证异常.";

    /** SUPER_ADMIN */
    public static final String ROLE_CODE_SUPER_ADMIN = "SUPER_ADMIN";

    /** ROOT */
    public static final String MODULE_CODE_ROOT = "ROOT";
}
