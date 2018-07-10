package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class SysController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysModuleService sysModuleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @RequestMapping("/")
    public String index() {
        return "redirect:" + systemSecurityProperties.getUrl().getLoginPageUrl();
    }

    @RequestMapping("/sys/menu")
    @ResponseBody
    public Object menu() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && EmptyUtil.notEmpty(auth.getName())) {
            return ResponseBean.successContent(sysModuleService.findMenuJSONByUsername(auth.getName()));
        } else {
            return null;
        }
    }

    @PostMapping("/sys/pwd/{username}")
    @ResponseBody
    public Object updatePwd(@PathVariable(value = "username", required = true)String username,
                            @RequestParam(value = "oldPwd", required = true)String oldPwd,
                            @RequestParam(value = "newPwd", required = true)String newPwd) {
        String authUsername = AuthUtil.getUsername();
        if (EmptyUtil.notEmpty(authUsername)) {
            SysUser user = sysUserService.findByUsername(AuthUtil.getUsername());
            if (EmptyUtil.notEmpty(username) && user != null
                    && username.equals(authUsername)
                    && new BCryptPasswordEncoder().matches(oldPwd, user.getPassword())) {
                user.setPassword(new BCryptPasswordEncoder().encode(newPwd));
                sysUserService.updateById(user);
                // TODO 记录日志
                return ResponseBean.success();
            }
        }
        return ResponseBean.getInstance(SysConstants.USERNAME_PWD_ERROR_CODE, "username or password error.", SysConstants.USERNAME_PWD_ERROR_MSG);
    }

    @GetMapping("/sys/user")
    @ResponseBody
    public Object user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SysUser user = sysUserService.findByUsername(auth.getName());
            if (user != null) {
                JSONObject content = new JSONObject();
                content.put("username", user.getUsername());
                content.put("realname", user.getRealname());
                return ResponseBean.successContent(content);
            }
        }
        return ResponseBean.getInstance(SysConstants.USER_AUTH_ERROR_CODE, SysConstants.USER_AUTH_ERROR_MSG, SysConstants.USER_AUTH_ERROR_SHOW_MSG);
    }

    @GetMapping("/sys/userDetail")
    @ResponseBody
    public Object userDetail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SysUser user = sysUserService.findSecurityUserByUsername(auth.getName());
            if(user != null) {
                JSONObject content = new JSONObject();
                content.put("username", user.getUsername());
                content.put("realname", user.getRealname());
                content.put("sex", user.getSex());
                content.put("age", user.getAge());
                content.put("address", user.getAddress());
                content.put("telephone", user.getTelephone());
                content.put("cellphone", user.getCellphone());
                content.put("userStatus", user.getUserStatus());
                content.put("roles", user.getRoles());
                return ResponseBean.successContent(content);
            }
        }
        return ResponseBean.getInstance(SysConstants.USER_AUTH_ERROR_CODE, SysConstants.USER_AUTH_ERROR_MSG, SysConstants.USER_AUTH_ERROR_SHOW_MSG);
    }
}
