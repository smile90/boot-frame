package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.common.frame.utils.EmptyUtil;
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
            return sysModuleService.findMenuJSONByUsername(auth.getName());
        } else {
            return null;
        }
    }

    @RequestMapping("/user")
    @ResponseBody
    public Object user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        } else {
            return null;
        }
    }
}
