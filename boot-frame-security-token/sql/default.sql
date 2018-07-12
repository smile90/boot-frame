-- 管理员
INSERT INTO `sys_user` (`ID`, `OPTIMISTIC`, `USER_NO`, `USERNAME`, `PASSWORD`, `REALNAME`, `EMAIL`, `TELEPHONE`, `CELLPHONE`, `SEX`, `AGE`, `ADDRESS`, `PHOTO`, `ORDERS`, `USER_STATUS`, `USER_TYPE_CODE`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (1, 6, '000000', 'admin', '$2a$10$02Ea.0bj6vczGLmVHGvFy.d6Vu5tmYmuNwRJhJz5AlWnOaWzCuIvC', '管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 'NORMAL', NULL, NULL, 'NORMAL', 'sql', NULL, NULL, NULL);

-- 角色
INSERT INTO `sys_role` (`ID`, `OPTIMISTIC`, `TYPE_CODE`, `NAME`, `CODE`, `ORDERS`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (1, 0, NULL, '超级管理员', 'SUPER_ADMIN', 0, '超级管理员，不允许变更', 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_role` (`ID`, `OPTIMISTIC`, `TYPE_CODE`, `NAME`, `CODE`, `ORDERS`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (2, 0, NULL, '管理员', 'ADMIN', 1, '管理员，不允许变更', 'NORMAL', 'sql', now(), NULL, NULL);

-- 模块
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (1, 0, '', '', 'ROOT', 'ROOT', '模块根路径', 'Y', 'Y', NULL, 0, NULL, '根路径模块，不允许变更', 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (2, 0, '-ROOT', 'ROOT', 'ROOT', 'PUBLIC', '公共模块', 'Y', 'Y', NULL, 0, NULL, NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (3, 0, '-ROOT-PUBLIC', 'PUBLIC', 'SYS_MODULE', 'PUBLIC_INDEX', '首页', 'N', 'Y', NULL, 1, NULL, NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (4, 0, '-ROOT-PUBLIC-PUBLIC_INDEX', 'PUBLIC_INDEX', 'SYS_MODULE', 'PUB_INDEX_INDEX', '主页', 'N', 'Y', NULL, 2, '/agent-boss/page/home/home.html', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (5, 0, '-ROOT-PUBLIC-PUBLIC_INDEX', 'PUBLIC_INDEX', 'SYS_MODULE', 'PUB_INDEX_MENU', '菜单', 'N', 'Y', NULL, 3, '/sys/menu', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (6, 0, '-ROOT-PUBLIC-PUBLIC_INDEX', 'PUBLIC_INDEX', 'SYS_FUNCTION', 'PUB_INDEX_USER', '个人信息', 'N', 'Y', NULL, 4, '/sys/user', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (7, 0, '-ROOT-PUBLIC-PUBLIC_INDEX', 'PUBLIC_INDEX', 'SYS_FUNCTION', 'PUB_INDEX_USERDETAIL', '个人详情', 'N', 'Y', NULL, 5, '/sys/userDetail', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (8, 0, '-ROOT-PUBLIC-PUBLIC_INDEX', 'PUBLIC_INDEX', 'SYS_FUNCTION', 'PUB_INDEX_WELCOME', '欢迎页面', 'N', 'Y', NULL, 6, '/sys/welcome', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (9, 0, '-ROOT-PUBLIC-PUBLIC_INDEX', 'PUBLIC_INDEX', 'SYS_FUNCTION', 'PUB_INDEX_PWD_UPDATE', '修改密码', 'N', 'Y', NULL, 7, '/sys/psw/*', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (10, 0, '-ROOT', 'ROOT', 'SYS_MENU', 'AUTH', '权限管理', 'Y', 'Y', NULL, 1, NULL, NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (11, 0, '-ROOT-AUTH', 'AUTH', 'SYS_MENU', 'AUTH_AUTH', '权限管理', 'Y', 'Y', NULL, 1, '/agent-boss/page/authority/authority.html', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (12, 0, '-ROOT-AUTH', 'AUTH', 'SYS_MENU', 'AUTH_ROLE', '角色管理', 'Y', 'Y', NULL, 2, '/agent-boss/page/role/role.html', NULL, 'NORMAL', 'sql', now(), NULL, NULL);
INSERT INTO `sys_module` (`ID`, `OPTIMISTIC`, `PARENT_PATH`, `PARENT_CODE`, `TYPE_CODE`, `CODE`, `NAME`, `VALIDATE`, `USEABLE`, `ICON`, `ORDERS`, `URL`, `DESCRIPTION`, `STATUS`, `CREATE_USER`, `CREATE_TIME`, `UPDATE_USER`, `UPDATE_TIME`) VALUES (13, 0, '-ROOT-AUTH', 'AUTH', 'SYS_MENU', 'AUTH_USER', '用户管理', 'Y', 'Y', NULL, 3, '/agent-boss/page/user/user.html', NULL, 'NORMAL', 'sql', now(), NULL, NULL);