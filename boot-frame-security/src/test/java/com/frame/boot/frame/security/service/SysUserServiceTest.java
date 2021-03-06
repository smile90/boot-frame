package com.frame.boot.frame.security.service;

import com.frame.boot.frame.security.base.BaseTest;
import com.frame.boot.frame.security.entity.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserServiceTest extends BaseTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void create() {
        SysUser user = new SysUser();
        user.setUsername("test1");
        logger.info("{}", user);
        sysUserService.create(user);
        logger.info("{}", user);
    }

    @Test
    public void findSecurityUserByUsername() {
        SysUser user = sysUserService.findSecurityUserByUsername("test2");
        logger.info("{}", user);
    }

}
