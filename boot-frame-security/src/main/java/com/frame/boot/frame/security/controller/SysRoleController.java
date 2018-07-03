package com.frame.boot.frame.security.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frame.boot.frame.mybatis.search.bean.SearchBuilder;
import com.frame.boot.frame.mybatis.search.bean.SearchData;
import com.frame.boot.frame.mybatis.search.bean.SearchType;
import com.frame.boot.frame.mybatis.search.bean.ValueType;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.service.SysRoleService;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/listPage")
    public Object listPage(Page<SysRole> page, @RequestParam Map<String,String> map) {
        SearchBuilder<SysRole> builder = new SearchBuilder<SysRole>();
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("name"))) {
            builder.build(new SearchData("name", SearchType.LIKE, ValueType.STRING, map.get("name")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("code"))) {
            builder.build(new SearchData("code", SearchType.EQ, ValueType.STRING, map.get("code")));
        }
        return ResponseBean.successContent(sysRoleService.selectPage(page, builder.build()));
    }

    @GetMapping("/list")
    public Object list(@RequestParam Map<String,String> map) {
        SearchBuilder<SysRole> builder = new SearchBuilder<SysRole>();
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("name"))) {
            builder.build(new SearchData("name", SearchType.LIKE, ValueType.STRING, map.get("name")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("code"))) {
            builder.build(new SearchData("code", SearchType.EQ, ValueType.STRING, map.get("code")));
        }
        return sysRoleService.selectList(builder.build());
    }

    @GetMapping("/get/{code}")
    public Object one(@PathVariable("code") String code) {
        return ResponseBean.successContent(sysRoleService.selectOne(new EntityWrapper<SysRole>().eq("code", code)));
    }

    @GetMapping("/exist/{code}/{selfCode}")
    public Object exist(@PathVariable("code") String code, @PathVariable("selfCode") String selfCode) {
        SysRole sysRole;
        if (EmptyUtil.notEmpty(selfCode)) {
            sysRole = sysRoleService.selectOne(new EntityWrapper<SysRole>().eq("code", code).ne("code", selfCode));
        } else {
            sysRole = sysRoleService.selectOne(new EntityWrapper<SysRole>().eq("code", code));
        }
        if (sysRole == null) {
            return ResponseBean.success();
        } else {
            return ResponseBean.getInstance(SysConstants.ROLE_EXIST_ERROR_CODE, "code is exist:" + code, SysConstants.CODE_EXIST_ERROR_SHOW_MSG);
        }
    }

    @PostMapping("/save")
    public Object save(SysRole role) {
        try {
            role.setCreateTime(new Date());
            role.setCreateUser(AuthUtil.getUsername());
            sysRoleService.insert(role);
            return ResponseBean.success();
        } catch (Exception e) {
            logger.error("save SysRole error. role:{}", role, e);
            return ResponseBean.getInstance(SysConstants.ROLE_ERROR_CODE, SysConstants.ROLE_ERROR_MSG, SysConstants.ROLE_ERROR_SHOW_MSG);
        }
    }

    @PostMapping("/update")
    public Object update(SysRole role) {
        try {
            SysRole dbRole = sysRoleService.selectById(role.getId());
            dbRole.setTypeCode(role.getTypeCode());
            dbRole.setCode(role.getCode());
            dbRole.setName(role.getName());
            dbRole.setStatus(role.getStatus());
            dbRole.setOrders(role.getOrders());
            dbRole.setDescription(role.getDescription());
            dbRole.setUpdateUser(AuthUtil.getUsername());
            dbRole.setUpdateTime(new Date());
            sysRoleService.updateById(dbRole);
            return ResponseBean.success();
        } catch (Exception e) {
            logger.error("save SysRole error. role:{}", role, e);
            return ResponseBean.getInstance(SysConstants.ROLE_ERROR_CODE, SysConstants.ROLE_ERROR_MSG, SysConstants.ROLE_ERROR_SHOW_MSG);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Long id) {
        try {
            sysRoleService.deleteById(id);
            return ResponseBean.success();
        } catch (Exception e) {
            logger.error("delete SysRole error. id:{}", id, e);
            return ResponseBean.getInstance(SysConstants.ROLE_ERROR_CODE, SysConstants.ROLE_ERROR_MSG, SysConstants.ROLE_ERROR_SHOW_MSG);
        }
    }
}
