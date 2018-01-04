package com.frame.boot.frame.security.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/page")
    public String page() {
        return "/sys/role/page";
    }

    @RequestMapping("/listPage")
    @ResponseBody
    public Object listPage(Page<SysRole> page) {
        if (page == null) {
            page = new Page<>();
        }
        return sysRoleService.selectPage(page);
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public Object update(@PathVariable("id") String id) {
        return sysRoleService.updateById(sysRoleService.selectOne(new EntityWrapper<SysRole>().eq("id", id)));
    }

}
