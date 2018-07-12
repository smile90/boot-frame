package com.frame.boot.frame.security.constants;

/**
 * 系统常量
 */
public class SysConstants {

    public static final String SYSTEM_CODE = "0001";

    /** SUPER_ADMIN */
    public static final String ROLE_CODE_SUPER_ADMIN = "SUPER_ADMIN";
    /** ADMIN */
    public static final String ROLE_CODE_ADMIN = "SUPER_ADMIN";

    /** ROOT */
    public static final String MODULE_CODE_ROOT = "ROOT";
    /** admin */
    public static final String USER_CODE_ADMIN = "admin";

    /** 公共 */
    public static final String PUBLIC_SYSTEM_CODE_COMMON = SYSTEM_CODE + "0001";
    /** 用户未登录 */
    public static final String COMMON_SYSTEM_ERROR_CODE = PUBLIC_SYSTEM_CODE_COMMON + "9999";
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

    /** 系统数据禁止修改 */
    public static final String SYS_DATA_MODIFY_ERROR_CODE = PUBLIC_SYSTEM_CODE_AUTH + "9997";
    public static final String SYS_DATA_MODIFY_ERROR_MSG = "sys data is not modification.";
    public static final String SYS_DATA_MODIFY_ERROR_SHOW_MSG = "系统数据禁止修改";

    /** 用户无权限 */
    public static final String USER_HAS_NO_AUTH_ERROR_CODE = PUBLIC_SYSTEM_CODE_AUTH + "9997";
    public static final String USER_HAS_NO_AUTH_ERROR_MSG = "user has no auth.";
    public static final String USER_HAS_NO_AUTH_ERROR_SHOW_MSG = "无操作权限";

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

    public static final String CODE_EXIST_ERROR_SHOW_MSG = "标识已存在";
}
