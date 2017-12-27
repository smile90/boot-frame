package com.frame.boot.frame.security.constants;

/**
 * 系统常量
 */
public class SysConstants {

    public static final String SYSTEM_NAME = "boot-frame-security";
    public static final String SYSTEM_CODE = "0001";

    /** 账号 */
    public static final String PUBLIC_SYSTEM_CODE_ACC = SYSTEM_CODE + "0001";

    public static final String USER_ERROR_MSG_BAD_CREDENTIALS = "用户/密码错误";
    public static final String USER_ERROR_MSG_BAD_VALID_CODE = "验证码错误";
    public static final String USER_ERROR_MSG_DISABLED = "用户已被禁用";
    public static final String USER_ERROR_MSG_EXPIRED = "账号已过期";
    public static final String USER_ERROR_MSG_LOCKED = "账号已被锁定";
    public static final String USER_ERROR_MSG_CREDENTIALS = "凭证已过期";

    /** SUPER_ADMIN */
    public static final String ROLE_CODE_SUPER_ADMIN = "SUPER_ADMIN";

    /** ROOT */
    public static final String MODULE_CODE_ROOT = "ROOT";
}
