package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys")
public class SysController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysModuleService sysModuleService;

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @RequestMapping("/login")
    public String login(Model model) {
        JSONObject loginModel = new JSONObject();
        loginModel.put("enableValidCode", systemSecurityProperties.isEnableValidCode());
        model.addAttribute("loginModel", loginModel);
        return "sys/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "sys/index";
    }
    @RequestMapping("/welcome")
    public String welcome() {
        return "sys/welcome";
    }
    @RequestMapping("/menu")
    @ResponseBody
    public Object menu() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && EmptyUtil.notEmpty(auth.getName())) {
            return ResponseBean.successContent(sysModuleService.findMenuJSONByUsername(auth.getName()));
        } else {
            return null;
        }
    }

    @RequestMapping("/user")
    @ResponseBody
    public Object user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SysUser user = (SysUser) auth.getPrincipal();
            JSONObject content = new JSONObject();
            content.put("username", user.getUsername());
            content.put("realname", user.getRealname());
            return ResponseBean.successContent(content);
        } else {
            return ResponseBean.getInstance(SysConstants.USER_AUTH_ERROR_CODE, SysConstants.USER_AUTH_ERROR_MSG, SysConstants.USER_AUTH_ERROR_SHOW_MSG);
        }
    }
}
