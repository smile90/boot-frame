package com.frame.boot.frame.security.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frame.boot.frame.search.SearchBuilder;
import com.frame.boot.frame.search.SearchData;
import com.frame.boot.frame.search.SearchType;
import com.frame.boot.frame.search.ValueType;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.service.SysRoleService;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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
    public Object listPage(Page<SysRole> page, @RequestParam Map<String,String> map) {
        SearchBuilder<SysRole> builder = new SearchBuilder<SysRole>();
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("name"))) {
            builder.build(new SearchData("name", SearchType.LIKE, ValueType.STRING, map.get("name")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("code"))) {
            builder.build(new SearchData("code", SearchType.EQ, ValueType.STRING, map.get("code")));
        }
        return sysRoleService.selectPage(page, builder.build());
    }
    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        return sysRoleService.selectList(null);
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public Object update(@PathVariable("id") String id) {
        return sysRoleService.updateById(sysRoleService.selectOne(new EntityWrapper<SysRole>().eq("id", id)));
    }

}
