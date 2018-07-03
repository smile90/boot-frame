-- 管理员
INSERT INTO `sys_user` VALUES ('1', '0', 'admin', '$2a$10$1B1qFlepHm/zN1P3hTs4IuJ57WE/nnpub3AMI7E4PSMa4/Fd7J5.C', '管理员', null, null, null, null, null, null, null, null, 'NORMAL', null, null, 'NORMAL', null, null, null, null);

-- ROOT模块
INSERT INTO `sys_module` VALUES ('1', '0', 'ROOT', 'ROOT', 'Y', 'Y', '公共模块', 'PUBLIC', null, '0', null, null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('2', '0', 'SYS_MODULE', 'PUBLIC', 'N', 'Y', '首页', 'PUBLIC_INDEX', null, '0', null, null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('3', '0', 'SYS_MODULE', 'PUBLIC_INDEX', 'N', 'Y', '主页', 'PUB_INDEX_INDEX', null, '1', '/sys/index', null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('4', '0', 'SYS_MODULE', 'PUBLIC_INDEX', 'N', 'Y', '菜单', 'PUB_INDEX_MENU', null, '2', '/sys/menu', null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('5', '0', 'SYS_FUNCTION', 'PUBLIC_INDEX', 'N', 'Y', '个人信息', 'PUB_INDEX_USER', null, '3', '/sys/user', null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('6', '0', 'SYS_FUNCTION', 'PUBLIC_INDEX', 'N', 'Y', '个人详情', 'PUB_INDEX_USERDETAIL', null, '4', '/sys/userDetail', null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('7', '0', 'SYS_FUNCTION', 'PUBLIC_INDEX', 'N', 'Y', '欢迎页面', 'PUB_INDEX_WELCOME', null, '5', '/sys/welcome', null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('8', '0', 'SYS_FUNCTION', 'PUBLIC_INDEX', 'N', 'Y', '修改密码', 'PUB_INDEX_PWD_UPDATE', null, '6', '/sys/psw/**', null, 'NORMAL', 'sql', null, null, null);

INSERT INTO `sys_module` VALUES ('9', '0', 'SYS_MENU', 'ROOT', 'Y', 'Y', '权限管理', 'AUTH', null, '1', null, null, 'NORMAL', 'sql', null, null, null);
INSERT INTO `sys_module` VALUES ('10', '0', 'SYS_MENU', 'AUTH', 'Y', 'Y', '角色管理', 'AUTH_ROLE', null, '1', '/sys/role/listPage', null, 'NORMAL', 'sql', null, null, null);