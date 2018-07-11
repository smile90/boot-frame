INSERT INTO `sys_module` VALUES ('1', '0', null, null, null, null, null, null, null, 'ROOT', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('2', '0', 'SYS_MENU', 'ROOT', null, null, null, null, null, 'TEST_M_1', null, null, '/test/m1', null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('3', '0', 'SYS_MENU', 'ROOT', null, null, null, null, null, 'TEST_M_2', null, null, '/test/m2', null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('4', '0', 'SYS_MENU', 'ROOT', null, null, null, null, null, 'TEST_M_3', null, null, '/test/m3', null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('5', '0', 'SYS_MENU', 'TEST_M_1', null, null, null, null, null, 'TEST_M_11', null, null, '/test/m11', null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('6', '0', 'SYS_MENU', 'TEST_M_1', null, null, null, null, null, 'TEST_M_12', null, null, '/test/m12', null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('7', '0', 'SYS_MODULE', 'TEST_M_11', null, null, null, null, null, 'TEST_M_111', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('8', '0', 'SYS_MODULE', 'TEST_M_11', null, null, null, null, null, 'TEST_M_112', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('9', '0', 'SYS_MODULE', 'TEST_M_111', null, null, null, null, null, 'TEST_M_1111', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('10', '0', 'SYS_MODULE', 'TEST_M_111', null, null, null, null, null, 'TEST_M_1112', null, null, null, null, null, null, null, null, null);

INSERT INTO `sys_function` VALUES ('1', '0', 'TEST_M_1111', null, null, null, 'TEST_F_111', null, '/test/f111', null, null, null, null, null, null);
INSERT INTO `sys_function` VALUES ('2', '0', 'TEST_M_1112', null, null, null, 'TEST_F_112', null, '/test/f112', null, null, null, null, null, null);
INSERT INTO `sys_function` VALUES ('3', '0', 'TEST_M_1111', null, null, null, 'TEST_F_1111', null, '/test/f1111', null, null, null, null, null, null);
INSERT INTO `sys_function` VALUES ('4', '0', 'TEST_M_1111', null, null, null, 'TEST_F_1112', null, '/test/f1112', null, null, null, null, null, null);
INSERT INTO `sys_function` VALUES ('5', '0', 'TEST_M_1111', null, null, null, 'TEST_F_1113', null, '/test/f1113', null, null, null, null, null, null);

INSERT INTO `sys_role` VALUES ('1', '0', null, '超级管理员', 'SUPER_ADMIN', null, null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('2', '0', null, '测试1', 'TEST1', null, null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('3', '0', null, '测试2', 'TEST2', null, null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('4', '0', null, '测试3', 'TEST3', null, null, null, null, null, null, null);

INSERT INTO `sys_user` VALUES ('1', '0', 'test1', 'e10adc3949ba59abbe56e057f20f883e', '测试1', null, null, null, null, null, null, null, null, 'NORMAL', null, null, null, 'NORMAL', null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', '0', 'test2', 'e10adc3949ba59abbe56e057f20f883e', '测试2', null, null, null, null, null, null, null, null, 'NORMAL', null, null, null, 'NORMAL', null, null, null, null);
INSERT INTO `sys_user` VALUES ('3', '0', 'test3', 'e10adc3949ba59abbe56e057f20f883e', '测试3', null, null, null, null, null, null, null, null, 'NORMAL', null, null, null, 'NORMAL', null, null, null, null);
INSERT INTO `sys_user` VALUES ('4', '0', 'test4', 'e10adc3949ba59abbe56e057f20f883e', '测试4', null, null, null, null, null, null, null, null, 'NORMAL', null, null, null, 'NORMAL', null, null, null, null);
INSERT INTO `sys_user` VALUES ('5', '0', 'test5', 'e10adc3949ba59abbe56e057f20f883e', '测试5', null, null, null, null, null, null, null, null, 'DELETED', null, null, null, 'NORMAL', null, null, null, null);
INSERT INTO `sys_user` VALUES ('6', '0', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', null, null, null, null, null, null, null, null, 'NORMAL', null, null, null, 'NORMAL', null, null, null, null);

INSERT INTO `sys_role_user` VALUES ('1', '0', 'SUPER_ADMIN', 'admin', null, null, null, null, null);
INSERT INTO `sys_role_user` VALUES ('2', '0', 'TEST1', 'test1', null, null, null, null, null);
INSERT INTO `sys_role_user` VALUES ('3', '0', 'TEST2', 'test2', null, null, null, null, null);
INSERT INTO `sys_role_user` VALUES ('4', '0', 'TEST3', 'test3', null, null, null, null, null);
INSERT INTO `sys_role_user` VALUES ('5', '0', 'TEST2', 'test4', null, null, null, null, null);
INSERT INTO `sys_role_user` VALUES ('6', '0', 'TEST3', 'test4', null, null, null, null, null);

INSERT INTO `sys_role_module` VALUES ('1', '0', 'TEST1', 'TEST_M_1', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('2', '0', 'TEST1', 'TEST_M_12', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('3', '0', 'TEST2', 'TEST_M_1', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('4', '0', 'TEST2', 'TEST_M_11', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('5', '0', 'TEST2', 'TEST_M_111', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('6', '0', 'TEST2', 'TEST_M_1111', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('7', '0', 'TEST2', 'TEST_M_1112', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('8', '0', 'TEST3', 'TEST_M_1', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('9', '0', 'TEST3', 'TEST_M_11', null, null, null, null, null);
INSERT INTO `sys_role_module` VALUES ('10', '0', 'TEST3', 'TEST_M_112', null, null, null, null, null);