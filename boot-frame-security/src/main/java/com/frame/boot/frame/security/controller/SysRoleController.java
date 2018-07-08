package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frame.boot.frame.mybatis.search.bean.SearchBuilder;
import com.frame.boot.frame.mybatis.search.bean.SearchData;
import com.frame.boot.frame.mybatis.search.bean.SearchType;
import com.frame.boot.frame.mybatis.search.bean.ValueType;
import com.frame.boot.frame.security.constants.RoleConstants;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.entity.SysRoleUser;
import com.frame.boot.frame.security.service.SysRoleModuleService;
import com.frame.boot.frame.security.service.SysRoleService;
import com.frame.boot.frame.security.service.SysRoleUserService;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleModuleService sysRoleModuleService;
    @Autowired
    private SysRoleUserService sysRoleUserService;

    @GetMapping("/listPage")
    public Object listPage(Page<SysRole> page, @RequestParam Map<String,String> map) {
        SearchBuilder<SysRole> builder = new SearchBuilder<>();
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("name"))) {
            builder.build(new SearchData("name", SearchType.LIKE, ValueType.STRING, map.get("name")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("code"))) {
            builder.build(new SearchData("code", SearchType.EQ, ValueType.STRING, map.get("code")));
        }
        return ResponseBean.successContent(sysRoleService.selectPage(page, builder.build()));
    }

    @GetMapping("/get/{code}")
    public Object one(@PathVariable("code") String code) {
        return ResponseBean.successContent(sysRoleService.selectOne(new EntityWrapper<SysRole>().eq("code", code)));
    }

    @GetMapping("/exist/{code}/{selfCode}")
    public Object exist(@PathVariable("code") String code, @PathVariable("selfCode") String selfCode) {
        if (!sysRoleService.exist(code, selfCode)) {
            return ResponseBean.success();
        } else {
            return ResponseBean.getInstance(RoleConstants.ROLE_EXIST_ERROR_CODE, "code is exist:" + code, SysConstants.CODE_EXIST_ERROR_SHOW_MSG);
        }
    }

    @PostMapping("/save")
    public Object save(SysRole role) {
        try {
            role.setCreateTime(new Date());
            role.setCreateUser(AuthUtil.getUsername());
            sysRoleService.insert(role);
        } catch (Exception e) {
            logger.error("save SysRole error. role:{}", role, e);
            return ResponseBean.getInstance(RoleConstants.ROLE_ERROR_CODE, RoleConstants.ROLE_ERROR_MSG, RoleConstants.ROLE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @PostMapping("/update")
    public Object update(SysRole role) {
        try {
            SysRole dbRole = sysRoleService.selectById(role.getId());
            // 系统数据不允许变更
            if (SysConstants.ROLE_CODE_SUPER_ADMIN.equals(dbRole.getCode()) || SysConstants.ROLE_CODE_ADMIN.equals(dbRole.getCode())) {
                return ResponseBean.getInstance(SysConstants.SYS_DATA_MODIFY_ERROR_CODE, SysConstants.SYS_DATA_MODIFY_ERROR_MSG, SysConstants.SYS_DATA_MODIFY_ERROR_SHOW_MSG);
            }
            sysRoleService.update(role, dbRole);
        } catch (Exception e) {
            logger.error("update SysRole error. role:{}", role, e);
            return ResponseBean.getInstance(RoleConstants.ROLE_ERROR_CODE, RoleConstants.ROLE_ERROR_MSG, RoleConstants.ROLE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Long id) {
        try {
            SysRole role = sysRoleService.selectById(id);
            if (role != null) {
                // 系统数据不允许变更
                if (SysConstants.ROLE_CODE_SUPER_ADMIN.equals(role.getCode()) || SysConstants.ROLE_CODE_ADMIN.equals(role.getCode())) {
                    return ResponseBean.getInstance(SysConstants.SYS_DATA_MODIFY_ERROR_CODE, SysConstants.SYS_DATA_MODIFY_ERROR_MSG, SysConstants.SYS_DATA_MODIFY_ERROR_SHOW_MSG);
                }
                sysRoleService.delete(role);
            }
        } catch (Exception e) {
            logger.error("delete SysRole error. id:{}", id, e);
            return ResponseBean.getInstance(RoleConstants.ROLE_ERROR_CODE, RoleConstants.ROLE_ERROR_MSG, RoleConstants.ROLE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @GetMapping("/modules/{roleCode}")
    public Object modulesByRoleCode(@PathVariable("roleCode") String roleCode) {
        return ResponseBean.successContent(sysRoleModuleService.selectList(new EntityWrapper<SysRoleModule>().eq("role_code", roleCode)));
    }

    @PostMapping("/updateRoleModules/{roleCode}")
    public Object updateRoleModules(@PathVariable("roleCode") String roleCode, @RequestParam String moduleCodes) {
        try {
            sysRoleModuleService.updateRoleModules(roleCode, JSONArray.parseArray(moduleCodes, String.class));
        } catch (Exception e) {
            logger.error("updateRoleModules error. roleCode:{},moduleCodes:{}", roleCode, moduleCodes, e);
            return ResponseBean.getInstance(RoleConstants.ROLE_ERROR_CODE, RoleConstants.ROLE_ERROR_MSG, RoleConstants.ROLE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @GetMapping("/users/{roleCode}")
    public Object usersByRoleCode(@PathVariable("roleCode") String roleCode) {
        return ResponseBean.successContent(sysRoleUserService.selectList(new EntityWrapper<SysRoleUser>().eq("role_code", roleCode)));
    }

    @PostMapping("/saveUser")
    public Object saveUser(@RequestParam("username") String username, @RequestParam("roleCode") String roleCode) {
        try {
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUsername(username);
            sysRoleUser.setRoleCode(roleCode);
            sysRoleUser.setCreateTime(new Date());
            sysRoleUser.setCreateUser(AuthUtil.getUsername());
            sysRoleUserService.insert(sysRoleUser);
        } catch (Exception e) {
            logger.error("updateRoleModules error. username:{},roleCode:{}", username, roleCode, e);
            return ResponseBean.getInstance(RoleConstants.ROLE_ERROR_CODE, RoleConstants.ROLE_ERROR_MSG, RoleConstants.ROLE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @DeleteMapping("/deleteUser/{username}")
    public Object deleteUser(@RequestParam("username") String username) {
        try {
            sysRoleUserService.deleteByUsername(username);
        } catch (Exception e) {
            logger.error("updateRoleModules error. username:{}", username, e);
            return ResponseBean.getInstance(RoleConstants.ROLE_ERROR_CODE, RoleConstants.ROLE_ERROR_MSG, RoleConstants.ROLE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

}
