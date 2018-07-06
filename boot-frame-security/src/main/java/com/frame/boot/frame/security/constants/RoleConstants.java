package com.frame.boot.frame.security.constants;

/**
 * 系统常量
 */
public class RoleConstants {

    /** 角色 */
    public static final String ROLE = SysConstants.SYSTEM_CODE + "0003";
    /** 角色相关异常 */
    public static final String ROLE_ERROR_CODE = ROLE + "9999";
    public static final String ROLE_ERROR_MSG = "role is error.";
    public static final String ROLE_ERROR_SHOW_MSG = "角色管理异常";

    /** 角色标识已存在 */
    public static final String ROLE_EXIST_ERROR_CODE = ROLE + "0001";
}
