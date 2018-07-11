package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.frame.boot.frame.mybatis.search.bean.SearchBuilder;
import com.frame.boot.frame.mybatis.search.bean.SearchData;
import com.frame.boot.frame.mybatis.search.bean.SearchType;
import com.frame.boot.frame.mybatis.search.bean.ValueType;
import com.frame.boot.frame.security.base.BaseTest;
import com.frame.boot.frame.security.entity.SysRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysRoleServiceTest extends BaseTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void find() {
        Page<SysRole> page = new Page<>();
        logger.info("----start:{}", page);
        page = sysRoleService.selectPage(page,
                new SearchBuilder<SysRole>()
//                        .build(new SearchData("id", SearchType.GE, ValueType.LONG, "1"))
//                        .and()
//                        .build(new SearchData("id", SearchType.LE, ValueType.LONG, "2"))
                        .build(new SearchData("name", SearchType.EQ, ValueType.STRING, "TEST1"))
                        .build());
        logger.info("----end:{}", page);
    }

}
