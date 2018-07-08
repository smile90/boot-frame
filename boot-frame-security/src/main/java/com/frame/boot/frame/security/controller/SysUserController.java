package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.plugins.Page;
import com.frame.boot.frame.mybatis.search.bean.SearchBuilder;
import com.frame.boot.frame.mybatis.search.bean.SearchData;
import com.frame.boot.frame.mybatis.search.bean.SearchType;
import com.frame.boot.frame.mybatis.search.bean.ValueType;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.constants.UserConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysRoleService;
import com.frame.boot.frame.security.service.SysRoleUserService;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleUserService sysRoleUserService;

    @GetMapping("/listPage")
    public Object listPage(Page<SysUser> page, @RequestParam Map<String,String> map) {
        SearchBuilder<SysUser> builder = new SearchBuilder<>();
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("realname"))) {
            builder.build(new SearchData("realname", SearchType.LIKE, ValueType.STRING, map.get("realname")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("username"))) {
            builder.build(new SearchData("username", SearchType.EQ, ValueType.STRING, map.get("username")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("userStatus"))) {
            builder.build(new SearchData("user_status", SearchType.EQ, ValueType.STRING, map.get("userStatus")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("status"))) {
            builder.build(new SearchData("status", SearchType.EQ, ValueType.STRING, map.get("status")));
        }
        return ResponseBean.successContent(sysUserService.selectPage(page, builder.build()));
    }

    @GetMapping("/get/{username}")
    public Object one(@PathVariable("username") String username) {
        return ResponseBean.successContent(sysUserService.findSecurityUserByUsername(username));
    }

    @GetMapping("/exist/{username}")
    public Object exist(@PathVariable("username") String username) {
        if (!sysUserService.exist(username)) {
            return ResponseBean.success();
        } else {
            return ResponseBean.getInstance(UserConstants.USER_EXIST_ERROR_CODE, "username is exist:" + username, SysConstants.CODE_EXIST_ERROR_SHOW_MSG);
        }
    }

    @PostMapping("/save")
    public Object save(SysUser user) {
        if (sysUserService.exist(user.getUsername())) {
            return ResponseBean.getInstance(UserConstants.USER_EXIST_ERROR_CODE, "username is exist:" + user.getUsername(), SysConstants.CODE_EXIST_ERROR_SHOW_MSG);
        }

        try {
            if (EmptyUtil.isEmpty(user.getPassword())) {
                user.setPassword(systemSecurityProperties.getDefualtUserPwd());
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setCreateTime(new Date());
            user.setCreateUser(AuthUtil.getUsername());
            sysUserService.insert(user);
        } catch (Exception e) {
            logger.error("save SysUser error. user:{}", user, e);
            return ResponseBean.getInstance(UserConstants.USER_ERROR_CODE, UserConstants.USER_ERROR_MSG, UserConstants.USER_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @PostMapping("/update")
    public Object update(SysUser user) {
        try {
            SysUser dbUser = sysUserService.selectById(user.getId());
            // 系统数据不允许变更
            if (SysConstants.USER_CODE_ADMIN.equals(dbUser.getUsername())) {
                return ResponseBean.getInstance(SysConstants.SYS_DATA_MODIFY_ERROR_CODE, SysConstants.SYS_DATA_MODIFY_ERROR_MSG, SysConstants.SYS_DATA_MODIFY_ERROR_SHOW_MSG);
            }
            dbUser.setUserNo(user.getUserNo());
            dbUser.setRealname(user.getRealname());
            dbUser.setCellphone(user.getCellphone());
            dbUser.setTelephone(user.getTelephone());
            dbUser.setAge(user.getAge());
            dbUser.setSex(user.getSex());
            dbUser.setEmail(user.getEmail());
            dbUser.setAddress(user.getAddress());
            dbUser.setPhoto(user.getPhoto());
            dbUser.setOrders(user.getOrders());
            dbUser.setUserStatus(user.getUserStatus());
            dbUser.setUpdateTime(new Date());
            dbUser.setUpdateUser(AuthUtil.getUsername());
            sysUserService.updateById(dbUser);
        } catch (Exception e) {
            logger.error("update SysUser error. user:{}", user, e);
            return ResponseBean.getInstance(UserConstants.USER_ERROR_CODE, UserConstants.USER_ERROR_MSG, UserConstants.USER_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Long id) {
        try {
            SysUser user = sysUserService.selectById(id);
            if (user != null) {
                // 系统数据不允许变更
                if (SysConstants.USER_CODE_ADMIN.equals(user.getUsername())) {
                    return ResponseBean.getInstance(SysConstants.SYS_DATA_MODIFY_ERROR_CODE, SysConstants.SYS_DATA_MODIFY_ERROR_MSG, SysConstants.SYS_DATA_MODIFY_ERROR_SHOW_MSG);
                }
                sysUserService.delete(user);
            }
        } catch (Exception e) {
            logger.error("delete SysUser error. id:{}", id, e);
            return ResponseBean.getInstance(UserConstants.USER_ERROR_CODE, UserConstants.USER_ERROR_MSG, UserConstants.USER_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @PostMapping("/pwdRest/{username}")
    @ResponseBody
    public Object updatePwd(@PathVariable(value = "username", required = true)String username) {
        if (EmptyUtil.notEmpty(username)) {
            SysUser user = sysUserService.findByUsername(username);
            if (user != null) {
                user.setPassword(new BCryptPasswordEncoder().encode(systemSecurityProperties.getDefualtUserPwd()));
                sysUserService.updateById(user);
                // TODO 记录日志
                return ResponseBean.success();
            }
        }
        return ResponseBean.getInstance(SysConstants.USERNAME_PWD_ERROR_CODE, "username or password error.", SysConstants.USERNAME_PWD_ERROR_MSG);
    }

    @GetMapping("/roles/{username}")
    public Object rolesByUsername(@PathVariable("username") String username) {
        return ResponseBean.successContent(sysRoleService.findByUsername(username));
    }

    @PostMapping("/updateRoleUsers/{username}")
    public Object updateRoleUsers(@PathVariable("username") String username, @RequestParam String roleCodes) {
        try {
            sysRoleUserService.updateRoleUsers(username, JSONArray.parseArray(roleCodes, String.class));
        } catch (Exception e) {
            logger.error("updateRoleUsers error. username:{},roleCodes:{}", username, roleCodes, e);
            return ResponseBean.getInstance(UserConstants.USER_ERROR_CODE, UserConstants.USER_ERROR_MSG, UserConstants.USER_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }
}
